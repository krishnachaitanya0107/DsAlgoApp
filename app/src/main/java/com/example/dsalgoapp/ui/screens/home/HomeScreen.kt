package com.example.dsalgoapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.dsalgoapp.navigation.Screen
import com.example.dsalgoapp.ui.components.ListContent
import com.example.dsalgoapp.util.Constants.data

@Composable
fun HomeScreen(navController: NavHostController) {

    Scaffold(
        content = {

            LazyColumn(
                contentPadding = PaddingValues(all = Dp(10f)),
                verticalArrangement = Arrangement.spacedBy(Dp(10f))
            ) {
                items(count = data.size) { item ->
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = data[item].title,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(Dp(20f)))

                    for (sublist in data[item].items.windowed(2, 2, true)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            sublist.forEach { res ->
                                ListContent(
                                    item = res,
                                    modifier = Modifier
                                        .clickable {
                                            if (item == 0) {
                                                navController.navigate(
                                                    Screen.AlgoDetail.passAlgoDetails(
                                                        arrayListOf(
                                                            item,
                                                            data[item].items.indexOf(res)
                                                        )
                                                    )
                                                )
                                            } else {
                                                navController.navigate(
                                                    Screen.DsDetail.passDsDetails(
                                                        arrayListOf(
                                                            item,
                                                            data[item].items.indexOf(res)
                                                        )
                                                    )
                                                )
                                            }
                                        }
                                        .fillParentMaxWidth(0.48f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(Dp(10f)))
                    }

                }
            }

        }
    )

}