package com.example.dsalgoapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object AlgoDetail : Screen("algo_detail_screen/{algo_details_id}") {
        fun passAlgoDetails(details: ArrayList<Int>): String {
            var res = "algo_detail_screen/"
            details.forEach {
                res = "$res$it "
            }
            return res
        }
    }
    object DsDetail : Screen("ds_detail_screen/{ds_details_id}") {
        fun passDsDetails(details: ArrayList<Int>): String {
            var res = "ds_detail_screen/"
            details.forEach {
                res = "$res$it "
            }
            return res
        }
    }
    object Search : Screen("search_screen/{search_id}") {
        fun passSearchDetails(searchId:String): String {
            return "search_screen/$searchId"
        }
    }
    object Sort : Screen("sort_screen/{sort_id}") {
        fun passSortDetails(sortId:String): String {
            return "sort_screen/$sortId"
        }
    }
}
