package com.example.dsalgoapp.ui.screens.real_time

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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
    }
}