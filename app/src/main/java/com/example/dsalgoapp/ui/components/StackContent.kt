package com.example.dsalgoapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dsalgoapp.ui.screens.ds_visualise.DsVisualiseViewModel
import com.example.dsalgoapp.ui.theme.*
import kotlin.random.Random


@Composable
fun StackContent(dsVisualiseViewModel: DsVisualiseViewModel) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Stack",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(10f)),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.titleColor
            )

            LazyColumn(
                contentPadding = PaddingValues(all = Dp(10f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(Dp(10f)),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(dsVisualiseViewModel.stack.size) { index ->
                    val color = if (index == 0) {
                        MaterialTheme.colors.green
                    } else {
                        Color.Black.copy(alpha = ContentAlpha.medium)
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = color,
                        shape = RoundedCornerShape(size = Dp(10f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dp(10f)),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "" + dsVisualiseViewModel.stack[dsVisualiseViewModel.stack.size - index - 1],
                                color = MaterialTheme.colors.titleColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Dp(10f)))
                }
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

            if (dsVisualiseViewModel.stack.isNotEmpty()) {
                Text("Top = ${dsVisualiseViewModel.stack.last()}", color = MaterialTheme.colors.green)
            } else {
                Text("Top = Null", color = MaterialTheme.colors.green)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (dsVisualiseViewModel.stack.size < 20) {
                            val temp = Random.nextInt(0, 100)
                            dsVisualiseViewModel.message = "Pushed $temp into stack"
                            dsVisualiseViewModel.pushInStack(temp)
                        } else {
                            dsVisualiseViewModel.message =
                                "Stack is full\n\nCannot push anymore elements\n\nPlease pop some elements first"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Push")
                }

                Button(
                    onClick = {
                        if (dsVisualiseViewModel.stack.isEmpty()) {
                            dsVisualiseViewModel.message =
                                "Stack is empty\n\nCannot pop any elements\n\nPlease push some elements first"
                        } else {
                            val removed = dsVisualiseViewModel.popInStack()
                            dsVisualiseViewModel.message = "Popped $removed from stack"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Pop")
                }
            }

        }
    }
}