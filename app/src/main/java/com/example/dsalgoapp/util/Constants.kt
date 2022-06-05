package com.example.dsalgoapp.util

import com.example.dsalgoapp.R
import com.example.dsalgoapp.data.DsAlgoItem
import com.example.dsalgoapp.data.DsAlgoSubItem
import com.example.dsalgoapp.data.DsAlgoDetailsItem

object Constants {

    const val ALGO_DETAILS_KEY = "algo_details_id"
    const val DS_DETAILS_KEY = "ds_details_id"
    const val INPUT_ARRAY_KEY = "input_array"
    const val NUMBER_TO_SEARCH_KEY = "num_to_search"
    const val SORT_ORDER_KEY = "sort_order"
    const val STEPS_TYPE_KEY = "steps_type"
    const val DS_ID = "ds_id"

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
                        /*, DsAlgoDetailsItem(
                            name = "Merge Sort",
                            id = "mergeSort",
                            description = "Merge Sort is a Divide and Conquer algorithm. It divides the input array into two halves, calls itself for the two halves, and then it merges the two sorted halves."
                        )*/
                    ),
                    id = "Sort"
                )
            )
        ), DsAlgoItem(
            title = "Data Structures",
            items = arrayListOf(
                DsAlgoSubItem(
                    name = "Stack",
                    image = R.drawable.stack,
                    id = "Stack",
                    description = "Stack is a linear data structure which follows a particular order in which the operations are performed. The order may be LIFO(Last In First Out) or FILO(First In Last Out).There are many real-life examples of a stack." +
                            " Consider an example of plates stacked over one another in the canteen. The plate which is at the top is the first one to be removed, i.e. the plate which has been placed at the bottommost position remains in the stack for the longest period of time."
                ), DsAlgoSubItem(
                    name = "Queue",
                    image = R.drawable.queue,
                    id = "Queue",
                    description = "Queue is an abstract data structure, somewhat similar to Stacks. Unlike stacks, a queue is open at both its ends. One end is always used to insert data (enqueue) and the other is used to remove data (dequeue). " +
                            "Queue follows First-In-First-Out methodology, i.e., the data item stored first will be accessed first.A real-world example of queue can be a single-lane one-way road, where the vehicle enters first, exits first. More real-world examples can be seen as queues at the ticket windows and bus-stops."
                ), DsAlgoSubItem(
                    name = "Linked List",
                    image = R.drawable.linked_list,
                    id = "LinkedList",
                    description = "A linked list is a sequence of data structures, which are connected together via links.Linked List is a sequence of links which contains items. Each link contains a connection to another link. Linked list is the second most-used data structure after array." +
                            "Linked list can be visualized as a chain of nodes, where every node points to the next node."
                )/*, DsAlgoSubItem(
                    name = "Tree",
                    image = R.drawable.tree,
                    id = "Tree",
                    description = "We have all watched trees from our childhood. It has roots, stems, branches and leaves. It was observed long back that each leaf of a tree can be traced to root via a unique path. Hence tree structure was used to explain hierarchical relationships, e.g. family tree, animal kingdom classification, etc." +
                            "This hierarchical structure of trees is used in Computer science as an abstract data type for various applications like data storage, search and sort algorithms.A tree is a hierarchical data structure defined as a collection of nodes. Nodes represent value and nodes are connected by edges."
                ), DsAlgoSubItem(
                    name = "Graph",
                    image = R.drawable.graph,
                    id = "Graph",
                    description = "A graph is a pictorial representation of a set of objects where some pairs of objects are connected by links. The interconnected objects are represented by points termed as vertices, and the links that connect the vertices are called edges.Mathematical graphs can be represented in data structure." +
                            " We can represent a graph using an array of vertices and a two-dimensional array of edges."
                )*/
            )
        )
    )

}