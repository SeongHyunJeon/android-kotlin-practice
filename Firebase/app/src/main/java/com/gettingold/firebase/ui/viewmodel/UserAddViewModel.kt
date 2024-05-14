package com.gettingold.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gettingold.firebase.data.remote.User
import com.gettingold.firebase.data.remote.repository.UserRegistrationAccessor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class UserEntryUiState(
    val user: User = User(),
    val validInput: Boolean = false
)

@HiltViewModel
class UserAddViewModel @Inject constructor(
    private val userRegistrationAccessor: UserRegistrationAccessor
): ViewModel() {
    var userAddUiState by mutableStateOf(UserEntryUiState())
        private set

    fun updateUiState(user: User) {
        userAddUiState = UserEntryUiState(user, validateInput(user))
    }

    private fun validateInput(user: User): Boolean {
        with(user) {
            return name.isNotBlank() && age.isNotBlank() && phoneNum.isNotBlank()
        }
    }

    fun addUser() {
        userRegistrationAccessor.addUser(userAddUiState.user)
    }
}