package com.example.categorygrid

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val courseName: Int,
    val courseNumber: Int,
    @DrawableRes val courseImage: Int
)