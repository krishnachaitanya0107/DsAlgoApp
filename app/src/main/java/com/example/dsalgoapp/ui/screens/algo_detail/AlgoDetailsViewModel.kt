package com.example.dsalgoapp.ui.screens.algo_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsalgoapp.R
import com.example.dsalgoapp.data.ListItem2
import com.example.dsalgoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlgoDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var details = ListItem2(name = "Loading...", image = R.drawable.ic_launcher_foreground, items = arrayListOf(), id = "")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val details_id = savedStateHandle.get<String>(Constants.ALGO_DETAILS_KEY)
            if (details_id != null) {
                val indices = details_id.split(" ")
                details = Constants.data[indices[0].toInt()].items[indices[1].toInt()]
            }
        }
    }

}