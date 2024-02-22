package com.example.restaurant.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurant.ui.utils.RestaurantContentType
import com.example.restaurant.ui.utils.RestaurantNavigationType

@Composable
fun RestaurantApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
){
    val viewModel: RestaurantViewModel = viewModel()
    val restaurantUiState = viewModel.uiState.collectAsState().value

    val navigationType: RestaurantNavigationType
    val contentType: RestaurantContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = RestaurantNavigationType.BOTTOM_NAVIGATION
            contentType = RestaurantContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = RestaurantNavigationType.NAVIGATION_RAIL
            contentType = RestaurantContentType.LIST_ONLY
        }
        else -> {
            navigationType = RestaurantNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = RestaurantContentType.LIST_AND_DETAIL
        }
    }

    RestaurantHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        restaurantUiState = restaurantUiState,
        onClickBackButton = { viewModel.onBackButtonClick() },
        onClickNavigationTab = { viewModel.updateUiStateDistrict(it) },
        onClickDetailsIcon = { viewModel.updateUiStateRestaurant(it) },
        modifier = modifier
    )
}