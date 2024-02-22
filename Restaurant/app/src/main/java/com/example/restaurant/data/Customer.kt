package com.example.restaurant.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Customer(
    val id: Long,
    val visitRestaurantId: Long,
    @StringRes val name: Int,
    @StringRes val visitingDay: Int,
    @StringRes val comment: Int,
    @DrawableRes val avatar: Int,
)