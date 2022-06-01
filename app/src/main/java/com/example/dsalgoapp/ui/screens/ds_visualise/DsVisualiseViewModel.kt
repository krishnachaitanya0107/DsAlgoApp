package com.example.dsalgoapp.ui.screens.ds_visualise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.dsalgoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DsVisualiseViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var stack by mutableStateOf(listOf<Int>())
    var queue by mutableStateOf(listOf<Int>())
    var linkedList by mutableStateOf(listOf<Int>())

    var message by mutableStateOf("")

    var dsId = ""

    var front = -1
    var rear = -1

    fun pushInStack(element: Int) {
        stack = stack + listOf(element)
    }

    fun popInStack(): Int {
        val last = stack.last()
        stack = stack.dropLast(1)
        return last
    }

    fun insertInLinkedList(element: Int, position: Int) {
        linkedList = linkedList.subList(0, position) + listOf(element) + linkedList.subList(position, linkedList.size)
    }

    fun deleteInLinkedList(position: Int) {
        linkedList = linkedList.subList(0, position) + linkedList.subList(position + 1, linkedList.size)
    }

    fun addInQueue(element: Int) {
        if (front == -1) {
            front++
        }
        queue = queue + listOf(element)
        rear++
    }

    fun removeFromQueue(): Int {
        rear--
        val first = queue.first()
        queue = queue.drop(1)
        if (queue.isEmpty()) {
            front--
        }
        return first
    }

    init {
        dsId = savedStateHandle.get<String>(Constants.DS_ID) ?: ""
    }
}