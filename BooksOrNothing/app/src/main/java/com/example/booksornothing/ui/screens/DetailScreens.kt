package com.example.booksornothing.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booksornothing.R
import com.example.booksornothing.network.Book

@Composable
fun DetailsScreen(
    paddingValues: PaddingValues,
    currentBook: Book?,
    networkState: NetworkState,
    onClickBook: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    if (currentBook == null) {
        DetailsScreenList(
            paddingValues = paddingValues,
            networkState = networkState as NetworkState.Success,
            onClickBook = onClickBook,
            modifier = modifier
        )
    } else {
        DetailsScreenBookDescription(
            paddingValues = paddingValues,
            currentBook = currentBook,
            modifier = modifier
        )
    }
}

@Composable
fun DetailsScreenList(
    paddingValues: PaddingValues,
    networkState: NetworkState.Success,
    onClickBook: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    val books = networkState.books
    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(dimensionResource(R.dimen.detail_list_column_padding))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.detail_list_row_padding))
        ) {
            Text(
                text = stringResource(R.string.detail_search_text, books.totalItems),
                style = MaterialTheme.typography.labelLarge
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(books.items, key = { book -> books.items.indexOf(book) }) { book ->
                DetailsScreenListCard(
                    book = book,
                    onClickBook = onClickBook,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun DetailsScreenListCard(
    book: Book,
    onClickBook: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(Color.Unspecified),
        modifier = modifier
            .clickable { onClickBook(book) }
            .height(dimensionResource(R.dimen.detail_list_card_height))
            .padding(dimensionResource(R.dimen.detail_list_card_padding))
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.detail_list_card_column_padding))
                .fillMaxSize()
        ) {
            Card(
                elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.detail_list_card_image_elevation)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.detail_list_card_image_height))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(book.volumeInfo.imageLinks.thumbnail.replace("http", "https"))
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_connection_error), //이미지를 로드하지 못한 경우의 이미지.
                    placeholder = painterResource(R.drawable.loading_img), //이미지를 로드하는 동안의 이미지.
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = if (book.volumeInfo.title.length >= 40) {
                    stringResource(R.string.detail_list_title, book.volumeInfo.title.substring(0..39))
                } else book.volumeInfo.title,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(vertical = dimensionResource(R.dimen.detail_list_title_padding_vertical))
            )
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = book.volumeInfo.authors[0],
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = book.volumeInfo.publisher,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun DetailsScreenBookDescription(
    paddingValues: PaddingValues,
    currentBook: Book,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(dimensionResource(R.dimen.detail_book_column_padding))
    ) {
        Row(
            modifier = Modifier
                .weight(0.4f)
                .padding(vertical = dimensionResource(R.dimen.detail_book_column_row_padding_horizontal))
        ) {
            Card(
                elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.detail_list_card_image_elevation)),
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = dimensionResource(R.dimen.detail_book_image_padding_end))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(currentBook.volumeInfo.imageLinks.thumbnail.replace("http", "https"))
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_connection_error), //이미지를 로드하지 못한 경우의 이미지.
                    placeholder = painterResource(R.drawable.loading_img), //이미지를 로드하는 동안의 이미지.
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = currentBook.volumeInfo.title,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.detail_book_title_padding_vertical))
                )
                Text(
                    text = currentBook.volumeInfo.authors[0],
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = currentBook.volumeInfo.publisher,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.detail_book_publisher_padding_bottom))
                )
                Text(
                    text = stringResource(R.string.detail_book_date_and_page, currentBook.volumeInfo.publishedDate, currentBook.volumeInfo.pageCount),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(top = dimensionResource(R.dimen.detail_book_description_column_padding_top))
        ) {
            Text(
                text = stringResource(R.string.detail_book_description_text_intro),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.detail_book_description_intro_padding_bottom))
            )
            Text(
                text = currentBook.volumeInfo.description,
                fontSize = 16.sp,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.tertiary)
            )
        }
    }
}