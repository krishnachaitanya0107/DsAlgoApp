package com.example.dsalgoapp.data

data class DsAlgoSubItem(
    val name: String,
    val image: Int,
    val description: String? = null,
    val items: ArrayList<DsAlgoDetailsItem> = arrayListOf(),
    val id:String
)
