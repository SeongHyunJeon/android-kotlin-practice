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

package com.example.bluromatic

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bluromatic.ui.BluromaticScreen
import com.example.bluromatic.ui.theme.BluromaticTheme

class BlurActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            BluromaticTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BluromaticScreen()
                }
            }
        }
    }
}

fun Context.getImageUri(): Uri {
    val resources = this.resources

    //Uri의 구조 "scheme://authority/path"
    //android.resource://com.example.bluromatic/drawable/android_cupcake
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE) //Uri의 구조중 scheme 설정.
        .authority(resources.getResourcePackageName(R.drawable.android_cupcake)) //Uri의 구조중 authority 설정.
        .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake)) //Uri의 구조중 path 설정.
        .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake)) //Uri의 구조중 path 설정.
        .build()
}
