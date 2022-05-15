package com.example.dsalgoapp.util

import com.example.dsalgoapp.R
import com.example.dsalgoapp.data.ListItem
import com.example.dsalgoapp.data.ListItem2
import com.example.dsalgoapp.data.ListItem3

object Constants {

    const val ALGO_DETAILS_KEY = "algo_details_id"
    const val DS_DETAILS_KEY = "ds_details_id"
    const val SEARCH_ID = "search_id"
    const val SORT_ID = "sort_id"

    val data = arrayListOf(
        ListItem(
            title = "Algorithms",
            items = arrayListOf(
                ListItem2(
                    name = "Searching",
                    image = R.drawable.ic_launcher_foreground,
                    items = arrayListOf(
                        ListItem3(
                            name = "Linear Search",
                            id = "linearSearch",
                            description = "Linear search is a sequential searching algorithm where we start from one end and check every element of the list until the desired element is found. It is the simplest searching algorithm."
                        ), ListItem3(
                            name = "Binary Search",
                            id = "binarySearch",
                            description = "Binary Search is a searching algorithm used in a sorted array by repeatedly dividing the search interval in half. The idea of binary search is to use the information that the array is sorted and reduce the time complexity"
                        )
                    ),
                    id = "Search"
                ), ListItem2(
                    name = "Sorting",
                    image = R.drawable.ic_launcher_foreground,
                    items = arrayListOf(
                        ListItem3(
                            name = "Bubble Sort",
                            id = "bubbleSort",
                            description = "Bubble sort is a sorting algorithm that compares two adjacent elements and swaps them until they are not in the intended order. Just like the movement of air bubbles in the water that rise up to the surface, each element of the array move to the end in each iteration. Therefore, it is called a bubble sort."
                        ), ListItem3(
                            name = "Selection Sort",
                            id = "selectionSort",
                            description = "Selection sort is a simple sorting algorithm. This sorting algorithm is an in-place comparison-based algorithm in which the list is divided into two parts, the sorted part at the left end and the unsorted part at the right end. Initially, the sorted part is empty and the unsorted part is the entire list."
                        ), ListItem3(
                            name = "Merge Sort",
                            id = "mergeSort",
                            description = "Merge Sort is a Divide and Conquer algorithm. It divides the input array into two halves, calls itself for the two halves, and then it merges the two sorted halves."
                        )
                    ),
                    id = "Sort"
                )
            )
        ), ListItem(
            title = "Data Structures",
            items = arrayListOf(
                ListItem2(
                    name = "Stack",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Stack"
                ), ListItem2(
                    name = "Queue",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Queue"
                ), ListItem2(
                    name = "Tree",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Tree"
                ), ListItem2(
                    name = "Graph",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Graph"
                ), ListItem2(
                    name = "Heap",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Heap"
                )
            )
        )
    )

}