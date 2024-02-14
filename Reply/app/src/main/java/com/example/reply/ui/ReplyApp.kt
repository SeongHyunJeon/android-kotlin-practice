/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.reply.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyContentType
import com.example.reply.ui.utils.ReplyNavigationType

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    val navigationType: ReplyNavigationType
    val contentType: ReplyContentType

    when (windowSize) { //화면 크기에 따른 탐색 유형 설정.
        WindowWidthSizeClass.Compact -> { //소형인 경우 하단 탐색.
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> { //중형인 경우 레일 탐색.
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> { //대형인 경우 영구 탐색과 목록 세부정보 레이아웃.
            navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ReplyContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
    }

    ReplyHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        replyUiState = replyUiState,
        onTabPressed = { mailboxType: MailboxType -> //카테고리 클릭에 대한 이벤트.
            viewModel.updateCurrentMailbox(mailboxType = mailboxType) //UI상태의 현재 카테고리를 파라미터로 받은 해당 카테고리로 변경.
            viewModel.resetHomeScreenStates() //UI상태의 현재 선택된 이메일을 해당 카테고리의 첫 번째 이메일로 변경하고, 홈 화면을 유지한다.
        },
        onEmailCardPressed = { email: Email -> //이메일 클릭에 대한 이벤트.
            viewModel.updateDetailsScreenStates(
                email = email
            ) //UI상태의 현재 선택된 이메일을 파라미터로 받은 이메일로 변경하고 홈 화면을 유지하지 않는다.
        },
        onDetailScreenBackPressed = { //시스템 뒤로 혹은 위로 버튼 클릭에 대한 이벤트.
            viewModel.resetHomeScreenStates() //UI상태의 현재 선택된 이메일을 해당 카테고리의 첫 번째 이메일로 변경하고, 홈 화면을 유지한다.
        },
        modifier = modifier
    )
}
