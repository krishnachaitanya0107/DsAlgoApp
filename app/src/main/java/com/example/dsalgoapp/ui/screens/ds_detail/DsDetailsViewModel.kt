package com.example.dsalgoapp.ui.screens.ds_detail

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
class DsDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var details = DsAlgoSubItem(name = "Loading...", image = R.drawable.ic_launcher_foreground, items = arrayListOf(), id = "")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val details_id = savedStateHandle.get<String>(Constants.DS_DETAILS_KEY)
            if (details_id != null) {
                val indices = details_id.split(" ")
                details = Constants.data[indices[0].toInt()].items[indices[1].toInt()]
            }
        }
    }

}