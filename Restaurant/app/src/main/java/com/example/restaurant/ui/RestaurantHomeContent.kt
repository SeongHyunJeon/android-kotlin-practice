package com.example.restaurant.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.restaurant.R
import com.example.restaurant.data.DistrictType
import com.example.restaurant.ui.theme.RestaurantTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantTopBar(
    currentSelectedDistrictType: DistrictType,
    isHomeScreen: Boolean,
    onClickBackButton: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_app_bar_name, stringResource(currentSelectedDistrictType.title)),
                style = MaterialTheme.typography.displayMedium
            )
        },
        navigationIcon = {
            if (!isHomeScreen) {
                IconButton(onClick = onClickBackButton) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.top_app_bar_back_button),
                        modifier = Modifier.size(dimensionResource(R.dimen.restaurant_home_top_bar_icon))
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun RestaurantListItem(
    foodPainting: Int,
    name: Int,
    simpleAddress: Int,
    signature: Int,
    onClickDetailsIcon: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(dimensionResource(R.dimen.restaurant_list_item_row_padding))
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Unspecified,
            ),
            modifier = Modifier
                .weight(0.3f)
        ) {
            Image(
                painter = painterResource(foodPainting),
                contentDescription = "",
            )
        }
        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(start = dimensionResource(R.dimen.restaurant_list_item_column_padding_start))
        ) {
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.headlineMedium
            )
            RestaurantTextWithIcon(
                icon = painterResource(R.drawable.baseline_location_on_24),
                text = stringResource(simpleAddress),
                size = R.dimen.restaurant_list_item_icon_size,
                style = MaterialTheme.typography.bodyMedium
            )
            RestaurantTextWithIcon(
                icon = painterResource(R.drawable.baseline_favorite_24),
                text = stringResource(signature),
                tint = Color.Red,
                size = R.dimen.restaurant_list_item_icon_size,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.home_into_detail_button_description),
            modifier = Modifier
                .weight(0.1f)
                .size(dimensionResource(R.dimen.restaurant_home_into_detail_icon))
                .clickable { onClickDetailsIcon() }
        )
    }
}

@Composable
fun RestaurantTextWithIcon(
    icon: Painter,
    contentDescription: String = "",
    text: String,
    tint: Color = Color.Black,
    size: Int,
    style: TextStyle,
    textIconGap: Int = R.dimen.restaurant_row_icon_text_padding_start,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier
                .size(dimensionResource(size))
        )
        Text(
            text = text,
            style = style,
            modifier = Modifier.padding(start = dimensionResource(textIconGap))
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RestaurantListItemPreview(){
    RestaurantTheme {
        Surface {
            RestaurantListItem(foodPainting = R.drawable.barbecue, name = R.string.jangteo_name, simpleAddress = R.string.jangteo_address_simple, signature = R.string.jangteo_signature, onClickDetailsIcon = {})
        }
    }
}