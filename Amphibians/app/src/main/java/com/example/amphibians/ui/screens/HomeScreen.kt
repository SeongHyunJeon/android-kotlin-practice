package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.Amphibian
import com.example.amphibians.ui.AmphibiansApp
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    uiState: AmphibiansUiState,
    retry: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier
) {
    when (uiState) {
        is AmphibiansUiState.Success -> SuccessScreen(amphibians = uiState.amphibians, paddingValues = paddingValues, modifier = modifier)
        is AmphibiansUiState.Error -> ErrorScreen(paddingValues = paddingValues, retry = retry, modifier = modifier)
        is AmphibiansUiState.Loading -> LoadingScreen(paddingValues = paddingValues, modifier = modifier)
    }
}

@Composable
fun SuccessScreen(
    amphibians: List<Amphibian>,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = paddingValues,
        modifier = modifier.padding(bottom = dimensionResource(R.dimen.success_screen_grid_padding_bottom))
    ) {
        items(items = amphibians, key = { amphibians.indexOf(it) }) { amphibian ->
            AmphibiansCard(
                amphibian = amphibian,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1.0f)
            )
        }
    }
}

@Composable
fun AmphibiansCard(
    amphibian: Amphibian,
    modifier: Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation)),
        modifier = modifier.padding(dimensionResource(R.dimen.card_padding))
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth()
                    .padding(start = dimensionResource(R.dimen.card_column_row_padding_start))
            ) {
                Text(text = amphibian.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = dimensionResource(R.dimen.card_text_name_padding_end)))
                Text(text = stringResource(R.string.card_type_form, amphibian.type), fontSize = 12.sp)
            }
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.card_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxWidth()
            )
            Text(
                text = amphibian.description,
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(dimensionResource(R.dimen.card_text_description_padding))
            )
        }
    }
}

@Composable
fun ErrorScreen(
    paddingValues: PaddingValues,
    retry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(paddingValues)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Button(onClick = retry) {
            Text(text = stringResource(R.string.error_retry_button))
        }
    }
}

@Composable
fun LoadingScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "",
        modifier = modifier.padding(paddingValues)
    )
}