package com.example.booksornothing.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksornothing.R
import com.example.booksornothing.ui.screens.BooksOrNothingUiState
import com.example.booksornothing.ui.screens.BooksOrNothingViewModel
import com.example.booksornothing.ui.screens.DetailsScreen
import com.example.booksornothing.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksOrNothingApp() {
    val booksViewModel: BooksOrNothingViewModel = viewModel(factory = BooksOrNothingViewModel.Factory)
    val booksUiState = booksViewModel.uiState.collectAsState().value

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BooksOrNothingTopAppBar(
            booksUiState = booksUiState,
            scrollBehavior = scrollBehavior,
            onBackButtonFromList = booksViewModel::initialiseUIState,
            onBackButtonFromBook = booksViewModel::onBackButtonClickFromBook
        ) },
    ) {
        if (booksUiState.isHomeScreen) {
            HomeScreen(
                paddingValues = it,
                currentSearchWord = booksUiState.currentSearchWord,
                onSearchBooks = booksViewModel::getBooks,
                onChangeValue = booksViewModel::updateUiStateCurrentSearchWord,
                onResetValue = booksViewModel::initialiseUIState,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            DetailsScreen(
                paddingValues = it,
                currentBook = booksUiState.currentBook,
                networkState = booksUiState.networkState,
                onClickBook = booksViewModel::updateUiStateCurrentBook,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksOrNothingTopAppBar(
    booksUiState: BooksOrNothingUiState,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackButtonFromList: () -> Unit,
    onBackButtonFromBook: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isHomeScreen = booksUiState.isHomeScreen

    CenterAlignedTopAppBar(
        title = { Text(
            text = if (isHomeScreen) stringResource(R.string.app_name)
            else booksUiState.currentSearchWord,
            style = MaterialTheme.typography.displayMedium
        ) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            AnimatedVisibility(visible = !isHomeScreen) {
                IconButton(onClick = {
                    if (booksUiState.currentBook == null) onBackButtonFromList()
                    else onBackButtonFromBook()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    )
}