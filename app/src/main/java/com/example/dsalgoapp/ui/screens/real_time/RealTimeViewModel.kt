package com.example.dsalgoapp.ui.screens.real_time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.dsalgoapp.data.SortingStep
import com.example.dsalgoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RealTimeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var stepsType = ""
    var sortOrder = ""
    var userInputNumberToSearch = ""
    var userInputArray = listOf(0, 0, 0, 0, 0, 0, 0, 0)

    var inputArray by mutableStateOf(listOf(0, 0, 0, 0, 0, 0, 0, 0))

    var currStep by mutableStateOf("")
    var linearSearchState by mutableStateOf(0)
    var binarySearchStates by mutableStateOf(listOf(-1,8,8))

    var numberFoundIndex by mutableStateOf(-1)

    var sortStep by mutableStateOf(SortingStep(step = "Loading", arrayState = "01234567", modifiedArrSize = 8))


    fun initArray(){
        userInputArray.forEachIndexed{ index, value ->
            updateInputArray(index = index, newNum = value)
        }
    }

    fun updateInputArray(index: Int, newNum: Int) {
        inputArray = inputArray.toMutableList().also { it[index] = newNum }
    }

    fun addBinarySearchStates(state: List<Int>) {
        emptyBinarySearchStates()
        binarySearchStates = binarySearchStates + state
    }

    fun emptyBinarySearchStates() {
        binarySearchStates = binarySearchStates.drop(binarySearchStates.size)
    }

    init {
        stepsType = savedStateHandle.get<String>(Constants.STEPS_TYPE_KEY) ?: ""
        userInputNumberToSearch =
            savedStateHandle.get<String>(Constants.NUMBER_TO_SEARCH_KEY) ?: "0"
        sortOrder = savedStateHandle.get<String>(Constants.SORT_ORDER_KEY) ?: "Ascending"
        val userInputArrayString = savedStateHandle.get<String>(Constants.INPUT_ARRAY_KEY)
        userInputArray =
            userInputArrayString?.split(" ")?.map { if (it.isBlank()) 0 else it.toInt() }
                ?.toList()
                ?: listOf(0, 0, 0, 0, 0, 0, 0, 0)

        if(stepsType.contains("binary")){
            userInputArray = userInputArray.sorted()
        }

    }
}