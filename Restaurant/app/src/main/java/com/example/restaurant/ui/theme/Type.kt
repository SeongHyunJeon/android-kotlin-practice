package com.example.restaurant.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.restaurant.R

val Dongle = FontFamily(
    Font(R.font.dongle_light, FontWeight.Light),
    Font(R.font.dongle_regular, FontWeight.Normal),
    Font(R.font.dongle_bold, FontWeight.Bold)
)

val Songmyung = FontFamily(
    Font(R.font.songmyung_regular, FontWeight.Normal)
)

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = Dongle,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Dongle,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Dongle,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Dongle,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Dongle,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = Dongle,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    )
)