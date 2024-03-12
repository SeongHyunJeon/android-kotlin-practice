package com.example.booksornothing.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.booksornothing.BooksOrNothingApplication
import com.example.booksornothing.data.BooksOrNothingRepository
import com.example.booksornothing.network.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class BooksOrNothingViewModel(
    val booksOrNothingRepository: BooksOrNothingRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(BooksOrNothingUiState())
    val uiState: StateFlow<BooksOrNothingUiState> = _uiState

    fun initialiseUIState() {
        _uiState.value = BooksOrNothingUiState()
    }

    fun updateUiStateCurrentSearchWord(word: String) {
        _uiState.update {
            it.copy(currentSearchWord = word)
        }
    }

    fun updateUiStateCurrentBook(book: Book) {
        _uiState.update {
            it.copy(currentBook = book)
        }
    }

    fun onBackButtonClickFromBook() {
        _uiState.update {
            it.copy(
                currentBook = null
            )
        }
    }

    fun getBooks() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                networkState = try {
                    val endpoint = "volumes?q=${it.currentSearchWord}"
                    NetworkState.Success(booksOrNothingRepository.getBooks(endpoint))
                } catch (e: IOException) {
                    NetworkState.Error
                },
                isHomeScreen = false
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BooksOrNothingApplication
                val repository = application.container.booksOrNothingRepository
                BooksOrNothingViewModel(repository)
            }
        }
    }
}