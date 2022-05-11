package com.example.dsalgoapp.data

data class ListItem2(
    val name: String,
    val image: Int,
    val description: String? = null,
    val items: ArrayList<ListItem3> = arrayListOf(),
    val id:String
)
