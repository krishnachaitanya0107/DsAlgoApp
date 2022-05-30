package com.example.dsalgoapp.ui.screens.ds_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.navigation.Screen

@Composable
fun DsDetailScreen(
    navController: NavController,
    dsDetailsViewModel: DsDetailsViewModel = hiltViewModel()
) {

    Scaffold(content = {
        LazyColumn(
            contentPadding = PaddingValues(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            items(count = 1) {

                val item = dsDetailsViewModel.details
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Black.copy(alpha = ContentAlpha.medium),
                    shape = RoundedCornerShape(size = Dp(10f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = Dp(10f))
                            .clickable {
                                navController.navigate(Screen.DsVisualise.passDsId(item.id))
                            },
                        verticalArrangement = Arrangement.spacedBy(Dp(10f))
                    ) {

                        Text(
                            text = item.name,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = item.description ?: "",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

            }
        }
    })

}