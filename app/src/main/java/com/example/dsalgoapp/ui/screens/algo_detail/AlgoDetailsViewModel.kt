package com.example.dsalgoapp.ui.screens.algo_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsalgoapp.R
import com.example.dsalgoapp.data.DsAlgoSubItem
import com.example.dsalgoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var details = DsAlgoSubItem(name = "Loading...", image = R.drawable.ic_launcher_foreground, items = arrayListOf(), id = "")

    var inputArray by mutableStateOf(listOf(0, 0, 0, 0, 0, 0, 0, 0))

    var numberToSearch = mutableStateOf("")

    var openSearchInputDialog = mutableStateOf(false)

    var openSortInputDialog = mutableStateOf(false)

    var openManualInputDialog = mutableStateOf(false)

    var selectedItemId = mutableStateOf("")

    var sortOrder = mutableStateOf("Ascending")

    fun updateInputArray(index: Int, newNum: Int) {
        inputArray = inputArray.toMutableList().also { it[index] = newNum }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val detailsId = savedStateHandle.get<String>(Constants.ALGO_DETAILS_KEY)
            if (detailsId != null) {
                val indices = detailsId.split(" ")
                details = Constants.data[indices[0].toInt()].items[indices[1].toInt()]
            }
        }
    }

}