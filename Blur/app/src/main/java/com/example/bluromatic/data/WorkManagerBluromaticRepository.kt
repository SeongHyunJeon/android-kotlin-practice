/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluromatic.data

import android.content.Context
import android.net.Uri
import androidx.lifecycle.asFlow
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.bluromatic.IMAGE_MANIPULATION_WORK_NAME
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.TAG_OUTPUT
import com.example.bluromatic.getImageUri
import com.example.bluromatic.workers.BlurWorker
import com.example.bluromatic.workers.CleanupWorker
import com.example.bluromatic.workers.SaveImageToFileWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapNotNull

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {

    private val imageUri: Uri = context.getImageUri()
    private val workManager = WorkManager.getInstance(context)

    //해당 태그의 작업 결과를 추적하는 LiveData<List<WorkInfo>>를 Flow<WorkInfo>로 변환하여 사용.
    override val outputWorkInfo: Flow<WorkInfo> = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
        .asFlow()
        .mapNotNull {
            if(it.isNotEmpty()) it.first() else null
        }

    /**
     * Create the WorkRequests to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    override fun applyBlur(blurLevel: Int) {
        //체인이 실행중이고, 동일한 체인의 실행 요청이 들어온 경우 ExistingWorkPolicy를 통해 조치 방법을 선택할 수 있다.
        var continuation = workManager.beginUniqueWork(IMAGE_MANIPULATION_WORK_NAME,ExistingWorkPolicy.REPLACE, OneTimeWorkRequest.Companion.from(CleanupWorker::class.java))
        val constraints = Constraints.Builder().setRequiresBatteryNotLow(true).build() //배터리 제약 생성.

        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
        blurBuilder.setInputData(createInputDataForWorkRequest(blurLevel, imageUri)) //BlurWorker에 데이터 전달.
        blurBuilder.setConstraints(constraints) //BlurWorker에 제약 전달.
        continuation = continuation.then(blurBuilder.build())

        val save =
            OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
                .addTag(TAG_OUTPUT)
                .build()
        continuation = continuation.then(save)
        continuation.enqueue()
    }

    /**
     * Cancel any ongoing WorkRequests
     * */
    override fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME) //해당 이름을 가진 체인의 작업을 취소한다.
    }

    /**
     * Creates the input data bundle which includes the blur level to
     * update the amount of blur to be applied and the Uri to operate on
     * @return Data which contains the Image Uri as a String and blur level as an Integer
     */
    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data { //Data 객체를 생성하는 도우미 함수.
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}
