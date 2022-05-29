package com.example.dsalgoapp.ui.screens.steps

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
class StepsViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel()  {

    var stepsType = ""
    var userInputArray = listOf(0, 0, 0, 0, 0, 0, 0, 0)
    var userInputNumberToSearch = ""
    var sortOrder = ""
    //var inputArray by mutableStateOf(userInputArray)
    //var numberToSearch = mutableStateOf(userInputNumberToSearch)

    var numberFound = mutableStateOf(false)

    var steps by mutableStateOf(listOf<String>())
    var sortSteps by mutableStateOf(listOf<SortingStep>())

    var binarySearchStates by mutableStateOf(listOf(listOf<Int>()))

    /*fun updateInputArray(index: Int, newNum: Int) {
        inputArray = inputArray.toMutableList().also { it[index] = newNum }
    }*/

    fun addSteps(step: String) {
        steps = steps + listOf(step)
    }

    fun emptySteps() {
        steps = steps.drop(steps.size)
    }

    fun addSteps(step: SortingStep) {
        sortSteps = sortSteps + listOf(step)
    }

    fun emptySortSteps(){
        sortSteps = sortSteps.drop(sortSteps.size)
    }

    fun addBinarySearchStates(state: List<Int>) {
        binarySearchStates = binarySearchStates + listOf(state)
    }

    fun emptyBinarySearchStates() {
        binarySearchStates = binarySearchStates.drop(binarySearchStates.size)
    }

    fun updateNumberFound(found: Boolean) {
        numberFound.value = found
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
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
}