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

    object Steps : Screen("steps_screen/{steps_type}/{sort_order}/{num_to_search}/{input_array}") {
        fun passStepsDetails(
            stepsType: String,
            inputArray: List<Int>,
            sortOrder: String,
            numToSearch: String
        ): String {
            var res = "steps_screen/$stepsType/$sortOrder/$numToSearch/"
            inputArray.forEachIndexed { index, it ->
                res = if (index == 0) {
                    "$res$it"
                } else {
                    "$res $it"
                }
            }
            return res
        }
    }

    object RealTime : Screen("rt_screen/{steps_type}/{sort_order}/{num_to_search}/{input_array}") {
        fun passDetails(
            stepsType: String,
            inputArray: List<Int>,
            sortOrder: String,
            numToSearch: String
        ): String {
            var res = "rt_screen/$stepsType/$sortOrder/$numToSearch/"
            inputArray.forEachIndexed { index, it ->
                res = if (index == 0) {
                    "$res$it"
                } else {
                    "$res $it"
                }
            }
            return res
        }
    }

}
