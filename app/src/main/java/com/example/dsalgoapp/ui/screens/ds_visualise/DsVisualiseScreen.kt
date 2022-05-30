package com.example.dsalgoapp.ui.screens.ds_visualise

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlin.random.Random

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

@Composable
fun DsContent(dsVisualiseViewModel: DsVisualiseViewModel) {
    if (dsVisualiseViewModel.dsId.contains("Stack")) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(all = Dp(10f)),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .padding(Dp(10f)),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(dsVisualiseViewModel.stack.size) { index ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.Black.copy(alpha = ContentAlpha.medium),
                        shape = RoundedCornerShape(size = Dp(10f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dp(10f)),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("" + dsVisualiseViewModel.stack[dsVisualiseViewModel.stack.size - index - 1])
                        }
                    }
                    Spacer(modifier = Modifier.height(Dp(10f)))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(Dp(10f)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(dsVisualiseViewModel.message)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (dsVisualiseViewModel.stack.size < 15) {
                                val temp = Random.nextInt(0, 100)
                                dsVisualiseViewModel.message = "Pushed $temp into stack"
                                dsVisualiseViewModel.pushInStack(temp)
                            } else {
                                dsVisualiseViewModel.message =
                                    "Stack Overflow\n\nCannot add anymore elements , Stack is full\n\nPlease pop some elements first"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Push")
                    }

                    Button(
                        onClick = {
                            if (dsVisualiseViewModel.stack.isEmpty()) {
                                dsVisualiseViewModel.message =
                                    "Stack Underflow\n\nCannot remove any elements , Stack is empty\n\nPlease push some elements first"
                            } else {
                                val removed = dsVisualiseViewModel.popInStack()
                                dsVisualiseViewModel.message = "Popped $removed from stack"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Pop")
                    }
                }

            }
        }
    } else if(dsVisualiseViewModel.dsId.contains("Queue")) {

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(all = Dp(10f)),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .padding(Dp(10f)),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(dsVisualiseViewModel.queue.size) { index ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.Black.copy(alpha = ContentAlpha.medium),
                        shape = RoundedCornerShape(size = Dp(10f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dp(10f)),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("" + dsVisualiseViewModel.queue[dsVisualiseViewModel.queue.size - index - 1])
                        }
                    }
                    Spacer(modifier = Modifier.height(Dp(10f)))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(Dp(10f)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(dsVisualiseViewModel.message)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (dsVisualiseViewModel.queue.size < 15) {
                                val temp = Random.nextInt(0, 100)
                                dsVisualiseViewModel.message = "Added $temp into queue"
                                dsVisualiseViewModel.addInQueue(temp)
                            } else {
                                dsVisualiseViewModel.message =
                                    "Stack Overflow\n\nCannot add anymore elements , Queue is full\n\nPlease remove some elements first"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Add")
                    }

                    Button(
                        onClick = {
                            if (dsVisualiseViewModel.queue.isEmpty()) {
                                dsVisualiseViewModel.message =
                                    "Stack Underflow\n\nCannot remove any elements , Queue is empty\n\nPlease add some elements first"
                            } else {
                                val removed = dsVisualiseViewModel.removeFromQueue()
                                dsVisualiseViewModel.message = "Removed $removed from queue"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Remove")
                    }
                }

            }
        }
    }
}