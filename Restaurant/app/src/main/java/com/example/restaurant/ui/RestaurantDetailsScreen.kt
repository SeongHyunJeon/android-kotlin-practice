package com.example.restaurant.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.restaurant.R
import com.example.restaurant.data.Customer
import com.example.restaurant.ui.utils.RestaurantContentType
import com.example.restaurant.ui.utils.RestaurantNavigationType

@Composable
fun RestaurantDetailScreen(
    restaurantUiState: RestaurantUiState,
    onBackButtonClick: () -> Unit,
    contentType: RestaurantContentType,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackButtonClick()
    }

    Column(
        modifier = modifier
    ) {
        AnimatedVisibility(visible = contentType == RestaurantContentType.LIST_ONLY) {
            RestaurantTopBar(
                currentSelectedDistrictType = restaurantUiState.currentSelectedDistrictType,
                isHomeScreen = restaurantUiState.isHomeScreen,
                onClickBackButton = onBackButtonClick
            )
        }
        LazyColumn(
            modifier = Modifier
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(restaurantUiState.currentSelectedRestaurant!!.map),
                        contentDescription = stringResource(R.string.detail_map_description),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    RestaurantDetailInformation(
                        restaurantUiState = restaurantUiState,
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.restaurant_details_information_padding))
                            .fillMaxWidth()
                    )
                }
            }
            items(
                restaurantUiState.currentSelectedRestaurant!!.customers,
                key = { customer -> customer.id }
            ) {
                RestaurantCommentListItem(customer = it)
            }
        }
    }
}

@Composable
fun RestaurantDetailInformation(
    restaurantUiState: RestaurantUiState,
    modifier: Modifier = Modifier
) {
    val restaurant = restaurantUiState.currentSelectedRestaurant!!

    Column(
        modifier = modifier
    ) {
        RestaurantTextWithIcon(
            icon = painterResource(R.drawable.baseline_location_on_24),
            text = stringResource(restaurant.detailedAddress),
            size = R.dimen.restaurant_detail_information_icon_size,
            style = MaterialTheme.typography.bodyLarge,
            textIconGap = R.dimen.restaurant_row_icon_text_detail_padding_start,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.restaurant_details_information_padding_bottom))
        )
        RestaurantTextWithIcon(
            icon = painterResource(R.drawable.baseline_access_time_24),
            text = stringResource(restaurant.weekdayOpeningHours),
            size = R.dimen.restaurant_detail_information_icon_size,
            style = MaterialTheme.typography.bodyLarge,
            textIconGap = R.dimen.restaurant_row_icon_text_detail_padding_start,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.restaurant_details_information_padding_bottom))
        )
        RestaurantTextWithIcon(
            icon = painterResource(R.drawable.baseline_access_time_filled_24),
            text = stringResource(restaurant.weekendOpeningHours),
            size = R.dimen.restaurant_detail_information_icon_size,
            style = MaterialTheme.typography.bodyLarge,
            textIconGap = R.dimen.restaurant_row_icon_text_detail_padding_start,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.restaurant_details_information_padding_bottom))
        )
        RestaurantTextWithIcon(
            icon = painterResource(R.drawable.baseline_phone_24),
            text = stringResource(restaurant.number),
            size = R.dimen.restaurant_detail_information_icon_size,
            style = MaterialTheme.typography.bodyLarge,
            textIconGap = R.dimen.restaurant_row_icon_text_detail_padding_start,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.restaurant_details_information_padding_bottom))
        )
    }
}

@Composable
fun RestaurantCommentListItem(
    customer: Customer,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.restaurant_detail_comment_card_elevation)),
        modifier = modifier.padding(dimensionResource(R.dimen.restaurant_detail_comment_card_padding))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.restaurant_detail_comment_column_padding))
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.restaurant_detail_comment_row_padding_top))
            ) {
                Image(
                    painter = painterResource(customer.avatar),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(0.3f)
                        .size(dimensionResource(R.dimen.restaurant_detail_comment_photo_size))
                )
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .weight(0.7f)
                ) {
                    Text(
                        text = stringResource(customer.name),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    RestaurantTextWithIcon(
                        icon = painterResource(R.drawable.baseline_calendar_month_24),
                        text = stringResource(customer.visitingDay),
                        size = R.dimen.restaurant_detail_comment_icon_size,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            Text(
                text = stringResource(customer.comment),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.restaurant_detail_comment_text_padding))
            )
        }
    }
}