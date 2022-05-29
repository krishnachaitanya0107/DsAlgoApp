package com.example.dsalgoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dsalgoapp.ui.screens.algo_detail.AlgoDetailScreen
import com.example.dsalgoapp.ui.screens.ds_detail.DsDetailScreen
import com.example.dsalgoapp.ui.screens.home.HomeScreen
import com.example.dsalgoapp.ui.screens.real_time.RealTimeScreen
import com.example.dsalgoapp.ui.screens.splash.SplashScreen
import com.example.dsalgoapp.ui.screens.steps.StepsScreen
import com.example.dsalgoapp.util.Constants

@ExperimentalComposeUiApi
@Composable
fun SetUpNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.AlgoDetail.route,
            arguments = listOf(
                navArgument(Constants.ALGO_DETAILS_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            AlgoDetailScreen(navController = navController)
        }
        composable(
            route = Screen.DsDetail.route,
            arguments = listOf(
                navArgument(Constants.DS_DETAILS_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            DsDetailScreen(navController = navController)
        }
        composable(
            route = Screen.Steps.route,
            arguments = listOf(
                navArgument(Constants.STEPS_TYPE_KEY) {
                    type = NavType.StringType
                }, navArgument(Constants.SORT_ORDER_KEY) {
                    type = NavType.StringType
                }, navArgument(Constants.NUMBER_TO_SEARCH_KEY) {
                    type = NavType.StringType
                }, navArgument(Constants.INPUT_ARRAY_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            StepsScreen(navController = navController)
        }

        composable(
            route = Screen.RealTime.route,
            arguments = listOf(
                navArgument(Constants.STEPS_TYPE_KEY) {
                    type = NavType.StringType
                }, navArgument(Constants.SORT_ORDER_KEY) {
                    type = NavType.StringType
                }, navArgument(Constants.NUMBER_TO_SEARCH_KEY) {
                    type = NavType.StringType
                }, navArgument(Constants.INPUT_ARRAY_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            RealTimeScreen(navController = navController)
        }

    }

}