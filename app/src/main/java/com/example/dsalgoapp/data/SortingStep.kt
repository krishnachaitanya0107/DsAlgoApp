package com.example.dsalgoapp.data

data class SortingStep(
    val step: String,
    val arrayState: String,
    val currentComparisons: ArrayList<Int> = arrayListOf(),
    val modifiedArrSize: Int = 0,
    val noOfComparisons: Int = 0,
    val noOfSwaps: Int = 0
)
