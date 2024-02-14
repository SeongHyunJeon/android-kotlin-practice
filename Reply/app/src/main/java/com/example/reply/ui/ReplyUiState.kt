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

import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(), //카테고리 별 이메일 리스트를 담은 맵.
    val currentMailbox: MailboxType = MailboxType.Inbox, //현재 선택된 카테고리.
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail, //현재 선택된 이메일.
    val isShowingHomepage: Boolean = true //홈 화면인지 아닌지를 구분하는 불리언 값.
) {
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! } //현재 선택된 카테고리에 해당하는 이메일 리스트.
}
