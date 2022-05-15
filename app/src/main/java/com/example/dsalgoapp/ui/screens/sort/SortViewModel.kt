package com.example.dsalgoapp.ui.screens.sort

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsalgoapp.data.SortingStep
import com.example.dsalgoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var sortType=""

    var inputArray by mutableStateOf(listOf(0, 0, 0, 0, 0, 0, 0, 0))

    var selectedOption by mutableStateOf("Ascending")

    var steps by mutableStateOf(listOf<SortingStep>())

    fun addSteps(step: SortingStep) {
        steps = steps + listOf(step)
    }

    fun emptySteps() {
        steps = steps.drop(steps.size)
    }

    fun updateInputArray(index: Int, newNum: Int) {
        inputArray = inputArray.toMutableList().also { it[index] = newNum }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            sortType = savedStateHandle.get<String>(Constants.SORT_ID) ?: ""
        }
    }
}