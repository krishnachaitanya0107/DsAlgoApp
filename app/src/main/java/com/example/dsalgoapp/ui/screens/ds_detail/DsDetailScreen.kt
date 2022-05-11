package com.example.dsalgoapp.ui.screens.ds_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DsDetailScreen(
    navController: NavController,
    dsDetailsViewModel: DsDetailsViewModel = hiltViewModel()
) {

    Scaffold(content = {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dp(10f)),
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dp(10f)),
        ) {
            dsDetailsViewModel.details.items.forEach {
                Text(
                    text = it.name,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(Dp(10f)))
                Text(
                    text = it.description,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(Dp(10f)))
            }
        }
    })

}