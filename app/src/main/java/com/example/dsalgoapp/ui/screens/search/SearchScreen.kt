package com.example.dsalgoapp.ui.screens.search

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
import com.example.dsalgoapp.util.Constants
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
                            Text(text = num.toString())
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
                    onClick = { generateRandomInput(searchViewModel = searchViewModel) },
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

fun generateLinearSearchSteps(searchViewModel: SearchViewModel) {
    searchViewModel.emptySteps()
    val arr = searchViewModel.inputArray
    val searchFor = searchViewModel.numberToSearch.value.toInt()
    var found = false
    for (a in arr.indices) {
        if (searchFor == arr[a]) {
            searchViewModel.addSteps("Element $searchFor found at index $a")
            found = true
            break
        } else {
            searchViewModel.addSteps("Comparing element at index $a \n${arr[a]} with $searchFor")
        }
    }
    if (!found) {
        searchViewModel.addSteps("Element $searchFor is not present in the array")
    }
}

fun generateBinarySearchSteps(searchViewModel: SearchViewModel) {
    searchViewModel.emptySteps()

    val arr = searchViewModel.inputArray
    val searchFor = searchViewModel.numberToSearch.value.toInt()

    var found = false
    var start = 0
    var end = arr.size - 1
    var mid: Int = ((start + end) / 2)

    searchViewModel.addSteps("Initializing values... \nstart = $start \nend = $end \nmid = (start+end)/2 = $mid")

    while (start <= end) {
        if (arr[mid] == searchFor) {
            searchViewModel.addSteps("Element $searchFor found at index $mid")
            found = true
            break
        } else {
            if (arr[mid] < searchFor) {
                searchViewModel.addSteps("Element $searchFor is greater than the mid element ${arr[mid]} \nUpdating start = ${mid + 1} \nUpdating mid = ${(mid + 1 + end) / 2} \nend = $end")
                start = mid + 1
            } else if (arr[mid] > searchFor) {
                searchViewModel.addSteps("Element $searchFor is lesser than the mid element ${arr[mid]} \nUpdating end = ${mid - 1} \nUpdating mid = ${(mid - 1 + start) / 2} \nstart = $start")
                end = mid - 1
            }
            mid = (start + end) / 2
        }
    }
    if (!found) {
        searchViewModel.addSteps("Element $searchFor is not present in the array")
    }
}
