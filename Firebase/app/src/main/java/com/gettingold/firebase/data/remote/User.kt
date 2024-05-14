package com.gettingold.firebase.data.remote

import com.gettingold.firebase.ui.viewmodel.UserEntryUiState

data class User(
    val id: String = "",
    val name: String = "",
    val age: String = "",
    val phoneNum: String = ""
) {
    fun toUserEditUiState(): UserEntryUiState = UserEntryUiState(this, true)
}
