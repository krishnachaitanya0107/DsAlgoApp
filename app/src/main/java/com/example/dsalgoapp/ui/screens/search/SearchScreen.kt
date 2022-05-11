package com.example.dsalgoapp.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.ui.theme.*
import kotlin.random.Random

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SettingsContent(searchViewModel = searchViewModel)
            StepsContent(searchViewModel = searchViewModel)
        }
    }
}

@Composable
fun StepsContent(searchViewModel: SearchViewModel) {

    LazyColumn(
        contentPadding = PaddingValues(all = Dp(10f)),
        verticalArrangement = Arrangement.spacedBy(Dp(10f))
    ) {
        items(count = searchViewModel.steps.size) { item ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = Dp(10f)),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        searchViewModel.inputArray.forEachIndexed { index, num ->
                            Box(
                                modifier =
                                Modifier
                                    .background(
                                        color = getColorState(item, index, searchViewModel),
                                        shape = RoundedCornerShape(size = Dp(1f))
                                    )
                            ) {
                                Text(text = num.toString(), modifier = Modifier.padding(Dp(3f)))
                            }
                            Spacer(modifier = Modifier.width(Dp(10f)))
                        }
                    }
                    Text(text = searchViewModel.steps[item])
                }
            }
            Spacer(modifier = Modifier.height(Dp(10f)))

        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SettingsContent(searchViewModel: SearchViewModel) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(10f)),
        color = Color.Black.copy(alpha = ContentAlpha.medium),
        shape = RoundedCornerShape(size = Dp(10f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text("Input Array :")

            Spacer(modifier = Modifier.height(Dp(10f)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                searchViewModel.inputArray.forEachIndexed { index, num ->
                    Text(text = num.toString())
                    Spacer(modifier = Modifier.width(Dp(10f)))
                }
            }

            Spacer(modifier = Modifier.height(Dp(10f)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /*to do*/ }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Manual Input")
                }
                Button(
                    onClick = {
                        reset(searchViewModel = searchViewModel)
                        generateRandomInput(searchViewModel = searchViewModel)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Randomize")
                }
            }

            Spacer(modifier = Modifier.height(Dp(10f)))

            OutlinedTextField(
                label = { Text(text = "Number to search :") },
                value = searchViewModel.numberToSearch.value,
                onValueChange = { new ->
                    if (!(new.length > 2 || new.any { !it.isDigit() })) {
                        searchViewModel.numberToSearch.value = new
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

            Spacer(modifier = Modifier.height(Dp(10f)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /*to do*/ }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Real Time Visualization")
                }
                Button(
                    onClick = { generateSteps(searchViewModel = searchViewModel) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Stepwise")
                }
            }

        }
    }

}

fun generateRandomInput(searchViewModel: SearchViewModel) {

    if (searchViewModel.searchType.contains("binary")) {
        searchViewModel.inputArray.forEachIndexed { index, _ ->
            val random = Random.nextInt(12 * (index), 12 * (index + 1))
            searchViewModel.updateInputArray(index = index, newNum = random)
        }
    } else if (searchViewModel.searchType.contains("linear")) {
        searchViewModel.inputArray.forEachIndexed { index, _ ->
            val random = Random.nextInt(0, 100)
            searchViewModel.updateInputArray(index = index, newNum = random)
        }
    }

}

fun generateSteps(searchViewModel: SearchViewModel) {

    if (searchViewModel.searchType.contains("linear")) {
        generateLinearSearchSteps(searchViewModel = searchViewModel)
    } else if (searchViewModel.searchType.contains("binary")) {
        generateBinarySearchSteps(searchViewModel = searchViewModel)
    }

}

fun getColorState(step: Int, index: Int, searchViewModel: SearchViewModel): Color {
    if (searchViewModel.searchType.contains("linear")) {
        if (searchViewModel.numberFound.value && searchViewModel.steps.size == step + 1) {
            return if (index < searchViewModel.steps.size - 1) {
                Gray
            } else if (index == searchViewModel.steps.size - 1) {
                Green
            } else {
                Color.Transparent
            }
        }
        return if (index < step) {
            Gray
        } else if (index == step) {
            Red
        } else {
            Color.Transparent
        }
    } else if (searchViewModel.searchType.contains("binary")) {
        if (step == 0) {
            return Color.Transparent
        }
        if(step>=searchViewModel.binarySearchStates.size){
            return Gray
        }

        val curState = searchViewModel.binarySearchStates[step]
        if (searchViewModel.numberFound.value) {
            if (step == searchViewModel.binarySearchStates.size - 1 && index == curState[1]) {
                return Green
            }
        }
        return if (index < curState[0] || index > curState[2]) {
            Gray
        } else if (index == curState[1]) {
            Red
        } else {
            Color.Transparent
        }

    } else {
        return Color.Transparent
    }
}

fun generateLinearSearchSteps(searchViewModel: SearchViewModel) {
    reset(searchViewModel)

    val arr = searchViewModel.inputArray
    val searchFor = searchViewModel.numberToSearch.value.toInt()

    for (i in arr.indices) {
        if (searchFor == arr[i]) {
            searchViewModel.addSteps("Element $searchFor found at index $i")
            searchViewModel.updateNumberFound(true)
            break
        } else {
            searchViewModel.addSteps("Comparing element at index $i \n${arr[i]} with $searchFor")
        }
    }
    if (!searchViewModel.numberFound.value) {
        searchViewModel.addSteps("Element $searchFor is not present in the array")
    }
}

fun generateBinarySearchSteps(searchViewModel: SearchViewModel) {
    reset(searchViewModel)

    val arr = searchViewModel.inputArray
    val searchFor = searchViewModel.numberToSearch.value.toInt()

    var start = 0
    var end = arr.size - 1
    var mid: Int = ((start + end) / 2)

    searchViewModel.addSteps("Initializing values... \nstart = $start \nend = $end \nmid = (start+end)/2 = $mid")
    searchViewModel.addBinarySearchStates(listOf(start, mid, end))

    while (start <= end) {
        if (arr[mid] == searchFor) {
            searchViewModel.addSteps("Element $searchFor found at index $mid")
            searchViewModel.addBinarySearchStates(listOf(start, mid, end))
            searchViewModel.updateNumberFound(true)
            break
        } else {
            if (arr[mid] < searchFor) {
                searchViewModel.addSteps("Element $searchFor is greater than the mid element ${arr[mid]} \nUpdating start = ${mid + 1} \nUpdating mid = ${(mid + 1 + end) / 2} \nend = $end")
                start = mid + 1
                searchViewModel.addBinarySearchStates(listOf(start, mid, end))
            } else if (arr[mid] > searchFor) {
                searchViewModel.addSteps("Element $searchFor is lesser than the mid element ${arr[mid]} \nUpdating end = ${mid - 1} \nUpdating mid = ${(mid - 1 + start) / 2} \nstart = $start")
                end = mid - 1
                searchViewModel.addBinarySearchStates(listOf(start, mid, end))
            }
            mid = (start + end) / 2
        }
    }

    if (!searchViewModel.numberFound.value) {
        searchViewModel.addSteps("Element $searchFor is not present in the array")
    }
}

fun reset(searchViewModel: SearchViewModel) {
    searchViewModel.emptySteps()
    searchViewModel.emptyBinarySearchStates()
    searchViewModel.updateNumberFound(false)
}