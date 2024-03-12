package com.example.booksornothing.ui.screens

import com.example.booksornothing.network.Book
import com.example.booksornothing.network.Books

data class BooksOrNothingUiState(
    val networkState: NetworkState = NetworkState.Loading,
    val currentSearchWord: String = "",
    val currentBook: Book? = null,
    val isHomeScreen: Boolean = true,
)

interface NetworkState {
    data class Success(val books: Books): NetworkState
    object Loading: NetworkState
    object Error: NetworkState
}