package com.example.dsalgoapp.util

import com.example.dsalgoapp.R
import com.example.dsalgoapp.data.DsAlgoItem
import com.example.dsalgoapp.data.DsAlgoSubItem
import com.example.dsalgoapp.data.DsAlgoDetailsItem

object Constants {

    const val ALGO_DETAILS_KEY = "algo_details_id"
    const val DS_DETAILS_KEY = "ds_details_id"
    const val SEARCH_ID = "search_id"
    const val SORT_ID = "sort_id"

    val data = arrayListOf(
        DsAlgoItem(
            title = "Algorithms",
            items = arrayListOf(
                DsAlgoSubItem(
                    name = "Searching",
                    image = R.drawable.ic_launcher_foreground,
                    items = arrayListOf(
                        DsAlgoDetailsItem(
                            name = "Linear Search",
                            id = "linearSearch",
                            description = "Linear search is a sequential searching algorithm where we start from one end and check every element of the list until the desired element is found. It is the simplest searching algorithm."
                        ), DsAlgoDetailsItem(
                            name = "Binary Search",
                            id = "binarySearch",
                            description = "Binary Search is a searching algorithm used in a sorted array by repeatedly dividing the search interval in half. The idea of binary search is to use the information that the array is sorted and reduce the time complexity"
                        )
                    ),
                    id = "Search"
                ), DsAlgoSubItem(
                    name = "Sorting",
                    image = R.drawable.ic_launcher_foreground,
                    items = arrayListOf(
                        DsAlgoDetailsItem(
                            name = "Bubble Sort",
                            id = "bubbleSort",
                            description = "Bubble sort is a sorting algorithm that compares two adjacent elements and swaps them until they are not in the intended order. Just like the movement of air bubbles in the water that rise up to the surface, each element of the array move to the end in each iteration. Therefore, it is called a bubble sort."
                        ), DsAlgoDetailsItem(
                            name = "Selection Sort",
                            id = "selectionSort",
                            description = "Selection sort is a simple sorting algorithm. This sorting algorithm is an in-place comparison-based algorithm in which the list is divided into two parts, the sorted part at the left end and the unsorted part at the right end. Initially, the sorted part is empty and the unsorted part is the entire list."
                        ), DsAlgoDetailsItem(
                            name = "Insertion Sort",
                            id = "insertionSort",
                            description = "Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands. The array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at the correct position in the sorted part."
                        )
                        , DsAlgoDetailsItem(
                            name = "Merge Sort",
                            id = "mergeSort",
                            description = "Merge Sort is a Divide and Conquer algorithm. It divides the input array into two halves, calls itself for the two halves, and then it merges the two sorted halves."
                        )
                    ),
                    id = "Sort"
                )
            )
        ), DsAlgoItem(
            title = "Data Structures",
            items = arrayListOf(
                DsAlgoSubItem(
                    name = "Stack",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Stack"
                ), DsAlgoSubItem(
                    name = "Queue",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Queue"
                ), DsAlgoSubItem(
                    name = "Linked List",
                    image = R.drawable.ic_launcher_foreground,
                    id = "LinkedList"
                ), DsAlgoSubItem(
                    name = "Tree",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Tree"
                ), DsAlgoSubItem(
                    name = "Graph",
                    image = R.drawable.ic_launcher_foreground,
                    id = "Graph"
                )
            )
        )
    )

}