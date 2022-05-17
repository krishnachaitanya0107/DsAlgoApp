package com.example.dsalgoapp.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.dsalgoapp.navigation.Screen
import kotlinx.coroutines.delay
import com.example.dsalgoapp.ui.theme.*

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(1200L)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    val bgModifier = if (isSystemInDarkTheme()) {
        Modifier
            .background(Color.Black.copy(alpha = ContentAlpha.medium))
            .fillMaxSize()
    } else {
        Modifier
            .background(Purple200)
            .fillMaxSize()
    }
    
    Column(modifier = bgModifier) {
        // to do
    }

}