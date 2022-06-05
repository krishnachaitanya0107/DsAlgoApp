package com.example.dsalgoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Gray = Color(0x50FFFFFF)
val Green = Color(0xFF115E14)
val LightGreen = Color(0xFF6CB359)
val Red = Color(0xFFF44336)

//val lightBlue = Color(0xFF10ACF3)
//val darkBlue = Color(0xFF3F51B5)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val Colors.statusBarColor
    get() = if (isLight) LightGreen else Color.Black

val Colors.titleColor
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionColor
    get() = if (isLight) DarkGray.copy(alpha = 0.5f)
    else LightGray.copy(alpha = 0.5f)

val Colors.buttonBackgroundColor
    get() = if (!isLight) Purple200 else Purple500

val Colors.topAppBarContentColor: Color
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    get() = if (isLight) Purple200 else Color.Black

val Colors.green: Color
    get() = if (isLight) LightGreen else Green

//val Colors.blue: Color
//    get() = if (isLight) darkBlue else lightBlue