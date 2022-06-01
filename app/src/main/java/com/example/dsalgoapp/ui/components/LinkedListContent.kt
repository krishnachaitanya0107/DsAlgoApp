package com.example.dsalgoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dsalgoapp.R
import com.example.dsalgoapp.ui.screens.ds_visualise.DsVisualiseViewModel
import com.example.dsalgoapp.ui.theme.buttonBackgroundColor
import com.example.dsalgoapp.ui.theme.titleColor

@ExperimentalComposeUiApi
@Composable
fun LinkedListContent(dsVisualiseViewModel: DsVisualiseViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var numToInsert by remember {
        mutableStateOf("")
    }

    var position by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.24f)
                .fillMaxWidth()
                .padding(Dp(10f))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Linked List",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.titleColor
                )

                LazyRow(
                    contentPadding = PaddingValues(all = Dp(10f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    items(dsVisualiseViewModel.linkedList.size) { index ->
                        if (index == 0) {
                            Image(
                                modifier = Modifier.fillMaxHeight(),
                                painter = painterResource(id = R.drawable.ic_arrow_right),
                                contentDescription = "Right arrow"
                            )
                        }
                        Surface(
                            modifier = Modifier
                                .fillMaxHeight(),
                            color = Color.Black.copy(alpha = ContentAlpha.medium),
                            shape = RoundedCornerShape(size = Dp(10f))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(Dp(10f)),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "" + dsVisualiseViewModel.linkedList.get(index = index),
                                    color = MaterialTheme.colors.titleColor
                                )
                            }
                        }
                        Image(
                            modifier = Modifier.fillMaxHeight(),
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "Right arrow"
                        )

                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(Dp(10f)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedTextField(
                    label = { Text(text = "Number to Insert :") },
                    value = numToInsert,
                    onValueChange = { new ->
                        if (!(new.length > 2 || new.any { !it.isDigit() })) {
                            numToInsert = new
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    ),
                    singleLine = true
                )

                OutlinedTextField(
                    label = { Text(text = "Position :") },
                    value = position,
                    onValueChange = { new ->
                        if (!(new.length > 2 || new.any { !it.isDigit() })) {
                            position = new
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    ),
                    singleLine = true
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (dsVisualiseViewModel.linkedList.size < 20) {
                                if (position.isEmpty() || numToInsert.isEmpty()) {
                                    dsVisualiseViewModel.message =
                                        "Number to insert or position was not defined"
                                } else if (position.toInt() > dsVisualiseViewModel.linkedList.size) {
                                    dsVisualiseViewModel.message = "Invalid position was defined"
                                } else {
                                    val pos = position.toInt()
                                    val num = numToInsert.toInt()
                                    dsVisualiseViewModel.message = when (pos) {
                                        0 -> {
                                            "Inserted $numToInsert into linked list ,\nChanging head to $numToInsert"
                                        }
                                        dsVisualiseViewModel.linkedList.size -> {
                                            "Inserted $numToInsert into linked list ,\nChanging tail to $numToInsert"
                                        }
                                        else -> {
                                            "Inserted $numToInsert into linked list"
                                        }
                                    }
                                    dsVisualiseViewModel.insertInLinkedList(num, pos)
                                }
                            } else {
                                dsVisualiseViewModel.message =
                                    "Linked list is full\n\nCannot insert anymore elements\n\nPlease remove some elements first"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                            contentColor =  Color.White
                        )
                    ) {
                        Text(text = "Insert")
                    }

                    Button(
                        onClick = {
                            if (dsVisualiseViewModel.linkedList.isEmpty()) {
                                dsVisualiseViewModel.message =
                                    "Linked list is empty\n\nCannot remove any elements\n\nPlease add some elements first"
                            } else {
                                if (position.isEmpty()) {
                                    dsVisualiseViewModel.message = "Position was not defined"
                                } else if (position.toInt() >= dsVisualiseViewModel.linkedList.size) {
                                    dsVisualiseViewModel.message = "Invalid position was defined"
                                } else {
                                    val pos = position.toInt()
                                    dsVisualiseViewModel.message = when (pos) {
                                        0 -> {
                                            if (pos + 1 >= dsVisualiseViewModel.linkedList.size) {
                                                "Removed ${dsVisualiseViewModel.linkedList[pos]} from linked list ,\nChanging head and tail to Null"
                                            } else {
                                                "Removed ${dsVisualiseViewModel.linkedList[pos]} from linked list ,\nChanging head to ${dsVisualiseViewModel.linkedList[pos + 1]}"
                                            }
                                        }
                                        dsVisualiseViewModel.linkedList.size -> {
                                            "Removed ${dsVisualiseViewModel.linkedList[pos]} from linked list ,\nChanging tail to $${dsVisualiseViewModel.linkedList[pos - 1]}"
                                        }
                                        else -> {
                                            "Removed ${dsVisualiseViewModel.linkedList[pos]} from linked list"
                                        }
                                    }
                                    dsVisualiseViewModel.deleteInLinkedList(pos)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Delete")
                    }
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.90f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (dsVisualiseViewModel.linkedList.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Head -> Null",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.titleColor
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Tail -> Null",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.titleColor
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Head -> ${dsVisualiseViewModel.linkedList.first()}",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.titleColor
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Tail -> ${dsVisualiseViewModel.linkedList.last()}",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.titleColor
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = dsVisualiseViewModel.message,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.titleColor
                )
            }
        }
    }
}