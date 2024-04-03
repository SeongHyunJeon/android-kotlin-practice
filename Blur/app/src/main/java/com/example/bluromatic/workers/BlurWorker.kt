package com.example.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.bluromatic.DELAY_TIME_MILLIS
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "BlurWorker"
class BlurWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        //inputData를 통해 Data객체를 전달 받을 수 있고, 저장된 값에 해당하는 키로 해당 작업에 지정된 입력값 혹은 이전 작업의 출력값을 전달 받을 수 있다.
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)
        makeStatusNotification(
            applicationContext.resources.getString(R.string.blurring_image),
            applicationContext
        )

        //doWork()은 기본적으로 Dispatchers.Default로 실행되지만 작업에 따라 적절한 스레드를 선택해야 한다.
        return withContext(Dispatchers.IO){
            delay(DELAY_TIME_MILLIS)

            return@withContext try {
                require(!resourceUri.isNullOrBlank()) {
                    val errorMessage =
                        applicationContext.resources.getString(R.string.invalid_input_uri)
                    Log.e(TAG, errorMessage)
                    errorMessage
                }
                val resolver = applicationContext.contentResolver
                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                ) //Uri를 비트맵으로 변환.

                val output = blurBitmap(picture, blurLevel) //해당 비트맵 이미지를 블러 처리.
                val outputUri = writeBitmapToFile(applicationContext, output) //블러 처리된 이미지를 파일로 저장.
                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString()) //해당 이미지의 Uri를 Data로 저장.
                Result.success(outputData) //반환.
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_blur),
                    throwable
                )
                Result.failure()
            }
        }

    }
}