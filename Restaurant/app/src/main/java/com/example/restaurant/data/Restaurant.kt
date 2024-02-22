package com.example.restaurant.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Restaurant(
    val id: Long,
    @StringRes val name: Int,
    @DrawableRes val map: Int,
    @DrawableRes val foodPainting: Int,
    @StringRes val weekdayOpeningHours: Int,
    @StringRes val weekendOpeningHours: Int,
    @StringRes val number: Int,
    @StringRes val simpleAddress: Int,
    @StringRes val detailedAddress: Int,
    @StringRes val signature: Int,
    val customers: List<Customer> = emptyList(),
    val district: DistrictType,
)