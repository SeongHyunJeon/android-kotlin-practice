package com.gettingold.firebase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gettingold.firebase.data.remote.User
import com.gettingold.firebase.data.remote.repository.UserRegistrationAccessor
import com.gettingold.firebase.ui.navigation.UserEditDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRegistrationAccessor: UserRegistrationAccessor
): ViewModel() {
    private val userId: String = checkNotNull(savedStateHandle[UserEditDestination.userIdArg])
    var userEditUiState by mutableStateOf(UserEntryUiState())
        private set

    init {
        viewModelScope.launch {
            userEditUiState = userRegistrationAccessor.getUser(userId)
                .filterNotNull()
                .first()
                .toUserEditUiState()
        }
    }

    fun updateUiState(user: User) {
        userEditUiState = UserEntryUiState(user, validateInput(user))
    }

    private fun validateInput(user: User): Boolean {
        with(user) {
            return name.isNotBlank() && age.isNotBlank() && phoneNum.isNotBlank()
        }
    }

    fun editUser() {
        userRegistrationAccessor.editUser(userEditUiState.user)
    }

}