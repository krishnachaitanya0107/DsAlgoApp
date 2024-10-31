package com.example.dsalgoapp.ui.screens.ds_visualise

import androidx.lifecycle.SavedStateHandle
import com.example.dsalgoapp.util.Constants
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Stack

class DsVisualiseViewModelTest {

    @Test
    fun initialStateTest() {
        val dsId = "Stack"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))

        val viewModel = DsVisualiseViewModel(savedStateHandle)

        // Assert initial values
        assertEquals(listOf<Int>(), viewModel.stack)
        assertEquals(listOf<Stack<Int>>(), viewModel.queue)
        assertEquals(listOf<Stack<Int>>(), viewModel.linkedList)
        assertEquals("", viewModel.message)
        assertEquals(-1, viewModel.front)
        assertEquals(-1, viewModel.rear)
    }

    @Test
    fun pushInStackTest() {
        val dsId = "Stack"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))
        val viewModel = DsVisualiseViewModel(savedStateHandle)
        val element = 5

        viewModel.pushInStack(element)

        assertEquals(viewModel.stack, listOf(element))
    }

    @Test
    fun popInStackTest() {
        val dsId = "Stack"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))
        val viewModel = DsVisualiseViewModel(savedStateHandle)

        val element1 = 10
        val element2 = 20

        viewModel.pushInStack(element1)
        viewModel.pushInStack(element2)

        val poppedElement = viewModel.popInStack()

        assertEquals(poppedElement, element2)
        assertEquals(viewModel.stack, listOf(element1))
    }

    @Test
    fun addInQueueTest() {
        val dsId = "Queue"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))
        val viewModel = DsVisualiseViewModel(savedStateHandle)

        val element = 5

        viewModel.addInQueue(element)

        assertEquals(viewModel.queue, listOf(element))
        assertEquals(viewModel.front, 0)
        assertEquals(viewModel.rear, 0)
    }

    @Test
    fun removeFromQueueTest() {
        val dsId = "Queue"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))
        val viewModel = DsVisualiseViewModel(savedStateHandle)

        val element1 = 10
        val element2 = 20

        viewModel.addInQueue(element1)
        viewModel.addInQueue(element2)

        val removedElement = viewModel.removeFromQueue()

        assertEquals(removedElement, element1)
        assertEquals(viewModel.queue, listOf(element2))
        assertEquals(viewModel.front, 0)
        assertEquals(viewModel.rear, 0)
    }

    // Assuming linkedList is initially empty

    @Test
    fun insertInLinkedListTest() {
        val dsId = "Linked List"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))
        val viewModel = DsVisualiseViewModel(savedStateHandle)

        val element = 5
        val position = 0

        viewModel.insertInLinkedList(element, position)

        assertEquals(viewModel.linkedList, listOf(element))
    }

    @Test
    fun deleteInLinkedListTest() {
        val dsId = "Linked List"
        val savedStateHandle = SavedStateHandle(mapOf(Pair(Constants.DS_ID, dsId)))
        val viewModel = DsVisualiseViewModel(savedStateHandle)

        val element1 = 10
        val element2 = 20

        viewModel.linkedList = listOf(element1, element2)
        val position = 0

        viewModel.deleteInLinkedList(position)

        assertEquals(viewModel.linkedList, listOf(element2))
    }
}