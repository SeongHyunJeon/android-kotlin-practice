package com.example.restaurant.ui

import androidx.lifecycle.ViewModel
import com.example.restaurant.data.DistrictType
import com.example.restaurant.data.Restaurant
import com.example.restaurant.data.local.LocalRestaurantsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RestaurantViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RestaurantUiState())
    val uiState: StateFlow<RestaurantUiState> = _uiState

    init {
        initialiseUIState()
    }

    private fun initialiseUIState() {
        val allRestaurants:Map<DistrictType, List<Restaurant>> = LocalRestaurantsDataProvider.allRestaurants.groupBy { it.district }

        _uiState.value = RestaurantUiState(
            allRestaurants = allRestaurants,
            currentSelectedDistrictType = DistrictType.Seogu,
        )
    }

    fun updateUiStateDistrict(districtType: DistrictType) {
        _uiState.update {
            it.copy(
                currentSelectedDistrictType = districtType,
                currentSelectedRestaurant = it.allRestaurants[districtType]?.get(0),
                isHomeScreen = true
            )
        }
    }

    fun updateUiStateRestaurant(restaurant: Restaurant) {
        _uiState.update {
            it.copy(
                currentSelectedRestaurant = restaurant,
                isHomeScreen = false
            )
        }
    }

    fun onBackButtonClick() {
        _uiState.update {
            it.copy(
                currentSelectedRestaurant = it.currentRestaurantList[0],
                isHomeScreen = true
            )
        }
    }
}