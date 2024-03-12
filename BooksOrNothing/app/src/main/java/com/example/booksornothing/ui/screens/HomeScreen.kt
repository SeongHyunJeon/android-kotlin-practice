package com.example.booksornothing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.example.booksornothing.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    currentSearchWord: String,
    onSearchBooks: () -> Unit,
    onChangeValue: (String) -> Unit,
    onResetValue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(paddingValues)
            .padding(dimensionResource(R.dimen.home_column_padding))
    ) {
        Image(
            painter = painterResource(R.drawable.hand_drawn_flat_design_book_spine_illustration_23_2149340856),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.home_image_height))
                .padding(bottom = dimensionResource(R.dimen.home_image_padding_bottom))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.home_image_clip)))
        )
        OutlinedTextField(
            value = currentSearchWord,
            onValueChange = { onChangeValue(it) },
            label = { Text(
                text = stringResource(R.string.home_textfield_label),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onSearchBooks() }
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.home_textfield_padding_bottom))
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { onSearchBooks() },
                modifier = Modifier
                    .padding(end = dimensionResource(R.dimen.home_button_padding_end))
                    .width(dimensionResource(R.dimen.home_button_width))
            ) {
                Text(
                    text = stringResource(R.string.home_button_search),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Button(
                onClick = { onResetValue() },
                modifier = Modifier.width(dimensionResource(R.dimen.home_button_width))
            ) {
                Text(
                    text = stringResource(R.string.home_button_eraser),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}