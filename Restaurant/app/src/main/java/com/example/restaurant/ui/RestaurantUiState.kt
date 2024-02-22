package com.example.restaurant.ui

import com.example.restaurant.data.DistrictType
import com.example.restaurant.data.Restaurant
import com.example.restaurant.data.local.LocalRestaurantsDataProvider
import com.example.restaurant.data.local.LocalRestaurantsDataProvider.allRestaurants

data class RestaurantUiState(
    val allRestaurants: Map<DistrictType, List<Restaurant>> = emptyMap(),
    val currentSelectedDistrictType: DistrictType = DistrictType.Seogu,
    val currentSelectedRestaurant: Restaurant? = LocalRestaurantsDataProvider.allRestaurants[0],
    val isHomeScreen: Boolean = true
) {
    //해당 변수가 사용될 때의 선택된 구의 식당 목록이 지정된다.
    val currentRestaurantList: List<Restaurant> by lazy { allRestaurants[currentSelectedDistrictType] ?: emptyList() }
}