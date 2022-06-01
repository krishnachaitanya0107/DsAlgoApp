package com.example.dsalgoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dsalgoapp.R
import com.example.dsalgoapp.ui.screens.ds_visualise.DsVisualiseViewModel
import com.example.dsalgoapp.ui.theme.*
import kotlin.random.Random


@Composable
fun QueueContent(dsVisualiseViewModel: DsVisualiseViewModel) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Queue",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dp(10f)),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.titleColor
            )

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Down arrow"
            )

            LazyColumn(
                contentPadding = PaddingValues(all = Dp(10f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(Dp(10f)),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(dsVisualiseViewModel.queue.size) { index ->
                    val color = when (index) {
                        0 -> {
                            Red
                        }
                        dsVisualiseViewModel.queue.size - 1 -> {
                            MaterialTheme.colors.green
                        }
                        else -> {
                            Color.Black.copy(alpha = ContentAlpha.medium)
                        }
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
                                text = "" + dsVisualiseViewModel.queue[dsVisualiseViewModel.queue.size - index - 1],
                                color = MaterialTheme.colors.titleColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Dp(10f)))
                }
            }

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Down arrow"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(Dp(10f)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(dsVisualiseViewModel.message)

            if (dsVisualiseViewModel.front != -1) {
                Text(
                    "Front = ${dsVisualiseViewModel.queue[dsVisualiseViewModel.front]}",
                    color = MaterialTheme.colors.green
                )
                Text(
                    "Rear = ${dsVisualiseViewModel.queue[dsVisualiseViewModel.rear]}",
                    color = Red
                )
            } else {
                Text(
                    "Front = Null",
                    color = MaterialTheme.colors.green
                )
                Text(
                    "Rear = Null",
                    color = Red
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (dsVisualiseViewModel.queue.size < 20) {
                            val temp = Random.nextInt(0, 100)
                            dsVisualiseViewModel.message = "Added $temp into queue"
                            dsVisualiseViewModel.addInQueue(temp)
                        } else {
                            dsVisualiseViewModel.message =
                                "Queue is full\n\nCannot add anymore elements\n\nPlease remove some elements first"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor =  Color.White
                    )
                ) {
                    Text(text = "Add")
                }

                Button(
                    onClick = {
                        if (dsVisualiseViewModel.queue.isEmpty()) {
                            dsVisualiseViewModel.message =
                                "Queue is empty\n\nCannot remove any elements\n\nPlease add some elements first"
                        } else {
                            val removed = dsVisualiseViewModel.removeFromQueue()
                            dsVisualiseViewModel.message = "Removed $removed from queue"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Remove")
                }
            }

        }

    }
}