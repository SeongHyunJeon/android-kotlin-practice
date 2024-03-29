package com.example.flightsearch.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightsearch.R

val Hahmlet = FontFamily(
    Font(R.font.hahmlet_bold, FontWeight.Bold),
    Font(R.font.hahmlet_regular)
)

val Jua = FontFamily(
    Font(R.font.jua_regular)
)

val Nanumgo = FontFamily(
    Font(R.font.nanumgothiccoding_regular)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Jua,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
    ), //상단바
    headlineMedium = TextStyle(
        fontFamily = Jua,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ), //상단바
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ), //본문 큰 글씨
    bodyMedium = TextStyle(
        fontFamily = Hahmlet,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ), //본문 작은 글씨
    bodySmall = TextStyle(
        fontFamily = Nanumgo,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ), //텍스트 필드
    labelMedium = TextStyle(
        fontFamily = Jua,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ) //플레이스 홀더
)