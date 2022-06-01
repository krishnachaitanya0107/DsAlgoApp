package com.example.dsalgoapp.ui.screens.ds_visualise

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.ui.components.*

@ExperimentalComposeUiApi
@Composable
fun DsVisualiseScreen(
    navController: NavController,
    dsVisualiseViewModel: DsVisualiseViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            DsContent(dsVisualiseViewModel = dsVisualiseViewModel)
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun DsContent(dsVisualiseViewModel: DsVisualiseViewModel) {
    if (dsVisualiseViewModel.dsId.contains("Stack")) {
        StackContent(dsVisualiseViewModel = dsVisualiseViewModel)
    } else if (dsVisualiseViewModel.dsId.contains("Queue")) {
        QueueContent(dsVisualiseViewModel = dsVisualiseViewModel)
    } else if(dsVisualiseViewModel.dsId.contains("Linked")){
        LinkedListContent(dsVisualiseViewModel = dsVisualiseViewModel)
    } else if(dsVisualiseViewModel.dsId.contains("Tree")){
        TreeContent(dsVisualiseViewModel = dsVisualiseViewModel)
    } else if(dsVisualiseViewModel.dsId.contains("Graph")){
        GraphContent(dsVisualiseViewModel = dsVisualiseViewModel)
    }
}