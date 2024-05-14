package com.gettingold.firebase.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gettingold.firebase.data.remote.User
import com.gettingold.firebase.data.remote.repository.UserRegistrationAccessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class UserListUiState(
    val userList: List<User> = listOf(),
)

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRegistrationAccessor: UserRegistrationAccessor
): ViewModel() {
    val userListUiState: StateFlow<UserListUiState> =
        userRegistrationAccessor.getAllUsers().map { UserListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserListUiState()
            )

    private val _swipedUserIdList = MutableStateFlow(listOf<String>())
    val swipedUserIdList: StateFlow<List<String>> = _swipedUserIdList

    fun addSwipedUserIntoList(userId: String) {
        if (_swipedUserIdList.value.contains(userId)) return

        _swipedUserIdList.value = _swipedUserIdList.value.toMutableList().also { list ->
            list.add(userId)
        }
    }

    fun removeSwipedUserIntoList(userId: String) {
        if (!_swipedUserIdList.value.contains(userId)) return
        _swipedUserIdList.value = _swipedUserIdList.value.toMutableList().also { list ->
            list.remove(userId)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun deleteUser(id: String) {
        userRegistrationAccessor.deleteUser(id)
    }

    fun deleteAllUsers() {
        userRegistrationAccessor.deleteAllUsers()
    }
}