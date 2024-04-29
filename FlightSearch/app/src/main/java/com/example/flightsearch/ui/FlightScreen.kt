package com.example.flightsearch.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightScreen(
    modifier: Modifier = Modifier,
    viewModel: FlightScreenViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { FlightTopAppBar(scrollBehavior) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            colorResource(R.color.background1),
                            colorResource(R.color.background1),
                            colorResource(R.color.background2),
                            colorResource(R.color.background3),
                            colorResource(R.color.background4),
                        ),
                        0f,
                        2000f
                    )
                )
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.flight_column_padding_top))
                .padding(horizontal = dimensionResource(R.dimen.flight_column_padding_horizontal))
        ) {
            TextField(
                value = uiState.value.currentText,
                shape = RoundedCornerShape(dimensionResource(R.dimen.flight_text_field_shape_round)),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Unspecified,
                    focusedIndicatorColor = Color.Unspecified,
                    containerColor = colorResource(R.color.text_field_container),
                    placeholderColor = colorResource(R.color.text_field_placeholder),
                    unfocusedLeadingIconColor = colorResource(R.color.text_field_icon),
                    focusedLeadingIconColor = colorResource(R.color.text_field_icon)
                ),
                onValueChange = {
                    viewModel.updateCurrentText(it)
                    viewModel.updateRelatedAirports(it)
                    viewModel.updateSelectedAirport(null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.flight_text_field_padding_bottom)),
                singleLine = true,
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                placeholder = { Text(
                    text = stringResource(R.string.textfield_placeholder_text),
                    style = Typography.labelMedium) },
                textStyle = Typography.bodySmall
            )
            AnimatedVisibility(visible = uiState.value.currentText.isEmpty() && uiState.value.favoriteAirports.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.favorites_message),
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.flight_info_text_padding_vertical))
                )
            }
            AnimatedVisibility(visible = uiState.value.currentText.isNotEmpty() && uiState.value.selectedAirport != null) {
                Text(
                    text = stringResource(R.string.available_flights_message, uiState.value.currentText),
                    style = Typography.headlineMedium,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.flight_info_text_padding_vertical))
                )
            }
            if (uiState.value.currentText.isEmpty()) { //텍스트 필드가 비어 있는 경우.
                if (uiState.value.favoriteAirports.isEmpty()) { //즐겨 찾기가 없는 경우.
                    FlightEmptyFavorite()
                } else { //즐겨 찾기가 있는 경우.
                    LazyColumn(modifier = Modifier) {
                        items(items = uiState.value.favoriteAirports, key = { it.id } ) { favorite ->
                            FlightAirportItem(
                                favorite = favorite,
                                insertFavorite = { viewModel.insertFavoriteAirport(it) },
                                deleteFavorite = { viewModel.deleteFavoriteAirport(it) },
                                isFavorite = { viewModel.isFavorite(it) }
                            )
                        }
                    }
                }
            } else { //텍스트 필드가 비어 있지 않은 경우.
                LazyColumn(modifier = Modifier) {
                    if (uiState.value.selectedAirport == null) { //출발할 공항을 선택하지 않은 경우.
                        items(items = uiState.value.relatedAirports, key = { it.id } ) { airport ->
                            FlightRelatedAirport(
                                iataCode = airport.iataCode,
                                airportName = airport.name,
                                onClickSelectedAirport = {
                                    viewModel.updateCurrentText(airport.iataCode)
                                    viewModel.updateSelectedAirport(airport)
                                    viewModel.updateAvailableAirports(airport.iataCode)
                                }
                            )
                        }
                    } else { //출발할 공항을 선택한 경우.
                        items(items = uiState.value.availableAirports, key = { it.id } ) { airport ->
                            val favorite = uiState.value.selectedAirport!!.toFavorite(airport)
                            FlightAirportItem(
                                favorite = favorite,
                                insertFavorite = { viewModel.insertFavoriteAirport(it) },
                                deleteFavorite = { viewModel.deleteFavoriteAirport(it) },
                                isFavorite = { viewModel.isFavorite(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(
            text = stringResource(R.string.top_bar_title),
            style = Typography.displayLarge,
            color = colorResource(R.color.top_bar_title),
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.flight_top_bar_text_padding_top))
                .padding(start = dimensionResource(R.dimen.flight_top_bar_text_padding_start))
        ) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = colorResource(R.color.background))
    )
}

@Composable
fun FlightEmptyFavorite(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = dimensionResource(R.dimen.flight_empty_column_padding_top))
    ) {
        Image(
            painter = painterResource(R.drawable.empty_concept_illustration_114360_7416),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(dimensionResource(R.dimen.flight_empty_image_width))
                .aspectRatio(1f)
                .clip(CircleShape)
                .padding(bottom = dimensionResource(R.dimen.flight_empty_image_padding_bottom))
        )
        Text(
            text = stringResource(R.string.favorites_empty_message1),
            style = Typography.bodyLarge,
            color = colorResource(R.color.empty_text_message1),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.flight_empty_text1_padding_bottom))
        )
        Text(
            text = stringResource(R.string.favorites_empty_message2),
            style = Typography.bodyMedium,
            color = colorResource(R.color.empty_text_message2)
        )
    }
}

@Composable
fun FlightRelatedAirport(
    iataCode: String,
    airportName: String,
    onClickSelectedAirport: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickSelectedAirport() }
            .padding(bottom = dimensionResource(R.dimen.flight_related_airport_row_padding_bottom))
    ) {
        Icon(
            imageVector = Icons.Default.Place,
            contentDescription = "",
            tint = colorResource(R.color.related_icon),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.flight_related_airport_icon_padding_horizontal))
        )
        Text(
            text = stringResource(R.string.related_airport_iata, iataCode),
            color = colorResource(R.color.related_text),
            modifier = Modifier.padding(end = dimensionResource(R.dimen.flight_related_airport_iata_padding_end))
        )
        Text(
            text = airportName,
            color = colorResource(R.color.font),
        )
    }
}

@Composable
fun FlightAirportItem(
    favorite: Favorite,
    isFavorite: (Favorite) -> Boolean,
    insertFavorite: (Favorite) -> Unit,
    deleteFavorite: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    val departureAirport = favorite.departureAirport
    val destinationAirport = favorite.destinationAirport
    var favoriteStatus by remember { mutableStateOf(isFavorite(favorite)) }

    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.flight_available_airport_card_shape_round)),
        colors = CardDefaults.cardColors(Color.Unspecified),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.flight_available_airport_card_elevation)),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.flight_available_airport_card_padding_bottom))
    ) {
        Row(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            colorResource(R.color.available_card_background),
                            colorResource(R.color.available_card_background1),
                            colorResource(R.color.available_card_background1),
                            colorResource(R.color.available_card_background2),
                            colorResource(R.color.available_card_background2),
                        ),
                        0f,
                        500f
                    )
                )
                .padding(dimensionResource(R.dimen.flight_available_airport_card_row_padding))
        ) {
            Column(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.flight_available_airport_depart_row_padding_bottom)),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_flight_takeoff_24),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.flight_available_airport_card_icon_padding_end))
                            .padding(top = dimensionResource(R.dimen.flight_available_airport_card_icon_padding_top)),
                        tint = Color.White
                    )
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = departureAirport.iataCode,
                            color = Color.White
                        )
                        Text(
                            text = departureAirport.name,
                            color = Color.White
                        )
                    }
                    Icon(
                        imageVector = if (favoriteStatus) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "",
                        tint = colorResource(R.color.available_favorite),
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.flight_available_airport_card_favorite_icon_padding_top))
                            .padding(end = dimensionResource(R.dimen.flight_available_airport_card_favorite_icon_padding_end))
                            .size(dimensionResource(R.dimen.flight_available_airport_card_favorite_icon_size))
                            .clickable {
                                if (favoriteStatus) deleteFavorite(favorite)
                                else insertFavorite(favorite)
                                favoriteStatus = favoriteStatus.not()
                            }
                    )
                }
                Row(
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_flight_land_24),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.flight_available_airport_card_icon_padding_end))
                            .padding(top = dimensionResource(R.dimen.flight_available_airport_card_icon_padding_top))
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = destinationAirport.iataCode
                        )
                        Text(
                            text = destinationAirport.name
                        )
                    }
                }
            }
        }
    }
}