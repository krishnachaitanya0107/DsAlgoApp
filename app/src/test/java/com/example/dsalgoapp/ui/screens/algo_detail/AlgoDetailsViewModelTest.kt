package com.example.dsalgoapp.ui.screens.algo_detail

import androidx.lifecycle.SavedStateHandle
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class AlgoDetailsViewModelTest {

    @Test
    fun initialStateTest() {
        val viewModel = AlgoDetailsViewModel(mockk<SavedStateHandle>())

        assertEquals(viewModel.details.name, "Loading...")
        assertEquals(viewModel.inputArray, listOf(0, 0, 0, 0, 0, 0, 0, 0))
        assertEquals(viewModel.numberToSearch.value, "")
        assertFalse(viewModel.openSearchInputDialog.value)
        assertFalse(viewModel.openSortInputDialog.value)
        assertFalse(viewModel.openManualInputDialog.value)
        assertEquals(viewModel.selectedItemId.value, "")
        assertEquals(viewModel.sortOrder.value, "Ascending")
        assertEquals(viewModel.selectedOption.value, "")
    }

    @Test
    fun updateInputArrayTest() {
        val viewModel = AlgoDetailsViewModel(mockk<SavedStateHandle>())
        val index = 2
        val newNum = 15

        viewModel.updateInputArray(index, newNum)

        assertEquals(viewModel.inputArray[index], newNum)
    }

}