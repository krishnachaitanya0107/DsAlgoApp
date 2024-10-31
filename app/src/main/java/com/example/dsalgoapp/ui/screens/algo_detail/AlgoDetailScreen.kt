package com.example.dsalgoapp.ui.screens.algo_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.navigation.Screen
import com.example.dsalgoapp.ui.theme.*
import kotlin.random.Random

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalComposeUiApi
fun AlgoDetailScreen(
    navController: NavController,
    algoDetailsViewModel: AlgoDetailsViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(content = {

        LazyColumn(
            contentPadding = PaddingValues(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            items(count = algoDetailsViewModel.details.items.size) { index ->
                AlgoListItem(
                    index = index,
                    algoDetailsViewModel = algoDetailsViewModel
                )
            }
        }
    })

    if (algoDetailsViewModel.openSearchInputDialog.value) {

        Dialog(onDismissRequest = { algoDetailsViewModel.openSearchInputDialog.value = false }) {
            Card(elevation = Dp(12f), shape = RoundedCornerShape(Dp(12f))) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dp(12f)),
                    verticalArrangement = Arrangement.spacedBy(Dp(10f))
                ) {

                    Text("Input Array :")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        algoDetailsViewModel.inputArray.forEachIndexed { index, num ->
                            Text(text = num.toString())
                            Spacer(modifier = Modifier.width(Dp(10f)))
                        }
                    }

                    Text("Input Type :")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { algoDetailsViewModel.openManualInputDialog.value = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Manual Input")
                        }
                        Button(
                            onClick = {
                                generateRandomInput(algoDetailsViewModel = algoDetailsViewModel)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Randomize")
                        }
                    }

                    OutlinedTextField(
                        label = { Text(text = "Number to search :") },
                        value = algoDetailsViewModel.numberToSearch.value,
                        onValueChange = { new ->
                            if (!(new.length > 2 || new.any { !it.isDigit() })) {
                                algoDetailsViewModel.numberToSearch.value = new
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


                    Button(
                        onClick = {
                            algoDetailsViewModel.openSearchInputDialog.value = false
                            if(algoDetailsViewModel.selectedOption.value.contains("StepWise")){
                                navController.navigate(
                                    Screen.Steps.passStepsDetails(
                                        stepsType = algoDetailsViewModel.selectedItemId.value,
                                        inputArray = algoDetailsViewModel.inputArray,
                                        sortOrder = algoDetailsViewModel.sortOrder.value,
                                        numToSearch = algoDetailsViewModel.numberToSearch.value.ifEmpty { "0" }
                                    )
                                )
                            } else if(algoDetailsViewModel.selectedOption.value.contains("RealTime")){
                                navController.navigate(
                                    Screen.RealTime.passDetails(
                                        stepsType = algoDetailsViewModel.selectedItemId.value,
                                        inputArray = algoDetailsViewModel.inputArray,
                                        sortOrder = algoDetailsViewModel.sortOrder.value,
                                        numToSearch = algoDetailsViewModel.numberToSearch.value.ifEmpty { "0" }
                                    )
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Let's Go")
                    }

                }
            }
        }
    }

    if (algoDetailsViewModel.openSortInputDialog.value) {

        Dialog(onDismissRequest = { algoDetailsViewModel.openSortInputDialog.value = false }) {
            Card(elevation = Dp(12f), shape = RoundedCornerShape(Dp(12f))) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dp(12f)),
                    verticalArrangement = Arrangement.spacedBy(Dp(10f))
                ) {

                    Text("Input Array :")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        algoDetailsViewModel.inputArray.forEachIndexed { index, num ->
                            Text(text = num.toString())
                            Spacer(modifier = Modifier.width(Dp(10f)))
                        }
                    }

                    Text("Input Type :")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { algoDetailsViewModel.openManualInputDialog.value = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Manual Input")
                        }
                        Button(
                            onClick = {
                                generateRandomInput(algoDetailsViewModel = algoDetailsViewModel)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Randomize")
                        }
                    }

                    Text("Sort Order :")

                    CustomRadioGroup(algoDetailsViewModel = algoDetailsViewModel)

                    Button(
                        onClick = {
                            algoDetailsViewModel.openSortInputDialog.value = false
                            if(algoDetailsViewModel.selectedOption.value.contains("StepWise")){
                                navController.navigate(
                                    Screen.Steps.passStepsDetails(
                                        stepsType = algoDetailsViewModel.selectedItemId.value,
                                        inputArray = algoDetailsViewModel.inputArray,
                                        sortOrder = algoDetailsViewModel.sortOrder.value,
                                        numToSearch = "0"
                                    )
                                )
                            } else if(algoDetailsViewModel.selectedOption.value.contains("RealTime")){
                                navController.navigate(
                                    Screen.RealTime.passDetails(
                                        stepsType = algoDetailsViewModel.selectedItemId.value,
                                        inputArray = algoDetailsViewModel.inputArray,
                                        sortOrder = algoDetailsViewModel.sortOrder.value,
                                        numToSearch = "0"
                                    )
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Let's Go")
                    }

                }
            }
        }
    }

    if (algoDetailsViewModel.openManualInputDialog.value) {

        Dialog(onDismissRequest = { algoDetailsViewModel.openManualInputDialog.value = false }) {
            Card(elevation = Dp(12f), shape = RoundedCornerShape(Dp(12f))) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dp(12f))
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(Dp(10f))
                ) {
                    Text(text = "Enter the input array")

                    Text(text = "Make sure the inputs are between 0 & 99")

                    for (i in 0 until 8) {
                        OutLineTextField(
                            algoDetailsViewModel = algoDetailsViewModel,
                            index = i
                        )
                    }

                    Button(
                        onClick = { algoDetailsViewModel.openManualInputDialog.value = false },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }


}

@Composable
@ExperimentalComposeUiApi
fun AlgoListItem(
    index: Int,
    algoDetailsViewModel: AlgoDetailsViewModel
) {

    val item = algoDetailsViewModel.details.items[index]

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.topAppBarBackgroundColor,
        shape = RoundedCornerShape(size = Dp(10f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            Text(
                text = item.name,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = item.description,
                modifier = Modifier.fillMaxWidth()
            )

            Text("Visualize :")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        algoDetailsViewModel.selectedOption.value = "RealTime"
                        if (item.id.contains("Search")) {
                            algoDetailsViewModel.openSearchInputDialog.value = true
                            algoDetailsViewModel.selectedItemId.value = item.id
                        } else if (item.id.contains("Sort")) {
                            algoDetailsViewModel.openSortInputDialog.value = true
                            algoDetailsViewModel.selectedItemId.value = item.id
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Real Time")
                }

                Button(
                    onClick = {
                        algoDetailsViewModel.selectedOption.value = "StepWise"
                        if (item.id.contains("Search")) {
                            algoDetailsViewModel.openSearchInputDialog.value = true
                            algoDetailsViewModel.selectedItemId.value = item.id
                        } else if (item.id.contains("Sort")) {
                            algoDetailsViewModel.openSortInputDialog.value = true
                            algoDetailsViewModel.selectedItemId.value = item.id
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Step Wise")
                }
            }
        }
    }

}

@Composable
@ExperimentalComposeUiApi
fun OutLineTextField(algoDetailsViewModel: AlgoDetailsViewModel, index: Int) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val currNum =
        rememberSaveable { mutableStateOf(algoDetailsViewModel.inputArray[index].toString()) }

    OutlinedTextField(
        value = currNum.value,
        onValueChange = { new ->
            if (!(new.length > 2 || new.any { !it.isDigit() })) {
                currNum.value = new
                if (new.isNotEmpty()) {
                    algoDetailsViewModel.updateInputArray(index = index, newNum = new.toInt())
                } else {
                    algoDetailsViewModel.updateInputArray(index = index, newNum = 0)
                }
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
        singleLine = true,
        label = { Text(text = "Enter element no ${index + 1}:") }
    )
}

fun generateRandomInput(algoDetailsViewModel: AlgoDetailsViewModel) {
    algoDetailsViewModel.inputArray.forEachIndexed { index, _ ->
        val random = Random.nextInt(0, 100)
        algoDetailsViewModel.updateInputArray(index = index, newNum = random)
    }
}


@Composable
fun CustomRadioGroup(algoDetailsViewModel: AlgoDetailsViewModel) {
    val options = listOf(
        "Ascending",
        "Descending"
    )

    val onSelectionChange = { text: String ->
        algoDetailsViewModel.sortOrder.value = text
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEach { text ->
            Text(text = text, modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        size = Dp(10f),
                    ),
                )
                .clickable {
                    onSelectionChange(text)
                }
                .background(
                    if (text == algoDetailsViewModel.sortOrder.value) {
                        Purple200
                    } else {
                        Gray
                    }
                )
                .padding(
                    vertical = Dp(10f),
                    horizontal = Dp(10f),
                )
            )

        }
    }

}
