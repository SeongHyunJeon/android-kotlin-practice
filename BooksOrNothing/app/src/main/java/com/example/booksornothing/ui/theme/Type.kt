package com.example.booksornothing.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.booksornothing.R

val Hahmlet = FontFamily(
    Font(R.font.hahmlet_bold, FontWeight.Bold),
    Font(R.font.hahmlet_regular, FontWeight.Normal)
)

val Sunflower = FontFamily(
    Font(R.font.sunflower_bold, FontWeight.Bold),
    Font(R.font.sunflower_medium, FontWeight.Normal)
)

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = Hahmlet,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ), //상단바 타이틀
    headlineMedium = TextStyle(
        fontFamily = Sunflower,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Hahmlet,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ), //텍스트 필드
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),//홈 화면 버튼
    labelMedium = TextStyle(
        fontFamily = Sunflower,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ), //책 제목
    labelSmall = TextStyle(
        fontFamily = Hahmlet,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = Color.Gray
    ) //저자, 출판사
)