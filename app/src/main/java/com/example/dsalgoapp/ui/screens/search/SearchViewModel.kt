package com.example.dsalgoapp.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsalgoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var searchType = ""

    var inputArray by mutableStateOf(listOf(0, 0, 0, 0, 0, 0, 0, 0))

    var numberToSearch = mutableStateOf("")

    var steps by mutableStateOf(listOf(""))


    fun updateInputArray(index: Int, newNum: Int) {
        inputArray = inputArray.toMutableList().also { it[index] = newNum }
    }

    fun addSteps(step: String) {
        steps = steps + listOf(step)
    }

    fun emptySteps() {
        steps = steps.drop(steps.size)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchType = savedStateHandle.get<String>(Constants.SEARCH_ID) ?: ""
        }
    }

}