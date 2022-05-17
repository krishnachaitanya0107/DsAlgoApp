package com.example.dsalgoapp.ui.screens.sort

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.data.SortingStep
import com.example.dsalgoapp.ui.theme.*
import kotlin.random.Random

@Composable
fun SortScreen(
    navController: NavController,
    sortViewModel: SortViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SettingsContent(sortViewModel = sortViewModel)
            StepsContent(sortViewModel = sortViewModel)
        }
    }
}

@Composable
fun SettingsContent(sortViewModel: SortViewModel) {

    val context= LocalContext.current

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
                sortViewModel.inputArray.forEachIndexed { index, num ->
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
                        sortViewModel.emptySteps()
                        generateRandomInput(sortViewModel = sortViewModel)
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

            Text("Sort Order :")

            Spacer(modifier = Modifier.height(Dp(10f)))

            CustomRadioGroup(sortViewModel = sortViewModel)

            Spacer(modifier = Modifier.height(Dp(10f)))

            Text("Visualize :")

            Spacer(modifier = Modifier.height(Dp(10f)))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { Toast.makeText( context,"Work In Progress..",Toast.LENGTH_SHORT).show() }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Real Time")
                }
                Button(
                    onClick = { generateSteps(sortViewModel = sortViewModel) },
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

@Composable
fun CustomRadioGroup(sortViewModel: SortViewModel) {
    val options = listOf(
        "Ascending",
        "Descending"
    )

    val onSelectionChange = { text: String ->
        sortViewModel.selectedOption = text
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
                    if (text == sortViewModel.selectedOption) {
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

@Composable
fun StepsContent(sortViewModel: SortViewModel) {
    LazyColumn(
        contentPadding = PaddingValues(all = Dp(10f)),
        verticalArrangement = Arrangement.spacedBy(Dp(10f))
    ) {
        items(count = sortViewModel.steps.size) { item ->

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
                        sortViewModel.steps[item].arrayState.forEachIndexed { index, num ->
                            Box(
                                modifier =
                                Modifier
                                    .background(
                                        color = getColorState(item, index, sortViewModel),
                                        shape = RoundedCornerShape(size = Dp(1f))
                                    )
                            ) {
                                Text(
                                    text = sortViewModel.inputArray[num.digitToInt()].toString(),
                                    modifier = Modifier.padding(Dp(3f))
                                )
                            }
                            Spacer(modifier = Modifier.width(Dp(10f)))
                        }
                    }
                    Text(text = sortViewModel.steps[item].step)
                }
            }
            Spacer(modifier = Modifier.height(Dp(10f)))

        }
    }
}

fun generateSteps(sortViewModel: SortViewModel) {
    if (sortViewModel.sortType.contains("bubble")) {
        generateBubbleSortSteps(sortViewModel = sortViewModel)
    } else if (sortViewModel.sortType.contains("selection")) {
        generateSelectionSortSteps(sortViewModel = sortViewModel)
    } else if(sortViewModel.sortType.contains("insertion")){
        generateInsertionSortSteps(sortViewModel = sortViewModel)
    }
}

fun getColorState(step: Int, index: Int, sortViewModel: SortViewModel): Color {
    if (sortViewModel.sortType.contains("bubble") || sortViewModel.sortType.contains("selection")) {
        val arrSize = sortViewModel.steps[step].modifiedArrSize
        val curPosition = sortViewModel.steps[step].currentComparisons

        return if (curPosition.contains(index))
            Gray
        else if (index > arrSize)
            Green
        else
            Color.Transparent
    } else if (sortViewModel.sortType.contains("insertion")) {
        val curPosition = sortViewModel.steps[step].currentComparisons

        return if (curPosition.contains(index))
            Gray
        else if (step == sortViewModel.steps.size-1)
            Green
        else
            Color.Transparent
    }
    return Gray
}

fun generateBubbleSortSteps(sortViewModel: SortViewModel) {
    sortViewModel.emptySteps()
    val numbers = arrayListOf<Int>()
    for (i in sortViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"

    val greaterOrLesser = if (sortViewModel.selectedOption == "Ascending") "greater" else "less"

    for (pass in 0 until size) {
        for (currentPosition in 0 until (size - pass)) {
            if (checkGreaterOrLesser(
                    num1 = numbers[currentPosition],
                    num2 = numbers[currentPosition + 1],
                    order = sortViewModel.selectedOption
                )
            ) {
                val temp = numbers[currentPosition]
                numbers[currentPosition] = numbers[currentPosition + 1]
                numbers[currentPosition + 1] = temp
                arrayState =
                    arrayState.substring(0, currentPosition) + arrayState[currentPosition + 1] +
                            arrayState[currentPosition] + arrayState.substring(currentPosition + 2)
                sortViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[currentPosition + 1]} is $greaterOrLesser than ${numbers[currentPosition]}\nSwapping ${numbers[currentPosition + 1]} with ${numbers[currentPosition]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass
                    )
                )
            } else {
                sortViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[currentPosition]} is not $greaterOrLesser than ${numbers[currentPosition + 1]}\nNo Swapping done",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass
                    )
                )
            }
        }
    }
    sortViewModel.addSteps(
        SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            modifiedArrSize = -1
        )
    )

}

fun generateSelectionSortSteps(sortViewModel: SortViewModel) {
    sortViewModel.emptySteps()
    val numbers = arrayListOf<Int>()
    for (i in sortViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"

    val greaterOrLesser = if (sortViewModel.selectedOption == "Ascending") "greater" else "less"
    val maxOrMin = if (sortViewModel.selectedOption == "Ascending") "max" else "min"

    for (i in size downTo 1) {
        var num = i
        for (j in 0 until i) {
            if (checkGreaterOrLesser(numbers[j], numbers[num], sortViewModel.selectedOption)) {
                sortViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[j]} is $greaterOrLesser than ${numbers[num]}\nSetting new $maxOrMin index to $j",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, num),
                        modifiedArrSize = i
                    )
                )
                num = j
            } else {
                sortViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[j]} is not $greaterOrLesser than ${numbers[num]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, num),
                        modifiedArrSize = i
                    )
                )
            }
        }
        if (i != num) {
            val temp = numbers[i]
            numbers[i] = numbers[num]
            numbers[num] = temp
            arrayState =
                arrayState.substring(0, num) + arrayState[i] + arrayState.substring(num + 1, i) +
            arrayState[num] + arrayState.substring(i + 1)
            sortViewModel.addSteps(
                SortingStep(
                    step = "Swapping ${numbers[num]} with ${numbers[i]}",
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(i, num),
                    modifiedArrSize = i
                )
            )
        } else {
            sortViewModel.addSteps(
                SortingStep(
                    step = "No Swapping performed",
                    arrayState = arrayState,
                    modifiedArrSize = i
                )
            )
        }
    }

    sortViewModel.addSteps(
        SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            modifiedArrSize = -1
        )
    )

}

fun generateInsertionSortSteps(sortViewModel: SortViewModel){
    sortViewModel.emptySteps()
    val numbers = arrayListOf<Int>()
    for (i in sortViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"

    val greaterOrLesser = if (sortViewModel.selectedOption == "Ascending") "greater" else "less"

    for (i in 1..size){
        val num = numbers[i]
        var hasMoved=false
        var notPlaced=true
        val t=arrayState[i]

        for(j in i-1 downTo 0){
            if(checkGreaterOrLesser(numbers[j],num,sortViewModel.selectedOption)){
                arrayState = arrayState.substring(0,j+1)+arrayState[j]+arrayState.substring(j+2)
                sortViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[j]} is $greaterOrLesser than $num\nShifting ${numbers[j]} to the right\nFinding position for $num",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j,j+1)
                    )
                )
                numbers[j+1]=numbers[j]
                hasMoved=true
            } else {
                arrayState = arrayState.substring(0,j+1)+t+arrayState.substring(j+2)
                sortViewModel.addSteps(
                    SortingStep(
                        step = "Inserting $num at index ${j+1}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j+1)
                    )
                )
                numbers[j+1]=num
                notPlaced=false
                break
            }
        }

        if(hasMoved && notPlaced){
            numbers[0]=num
            arrayState=t+arrayState.substring(1)
            sortViewModel.addSteps(
                SortingStep(
                    step = "Inserting $num at index 0",
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(0)
                )
            )
        }

    }

    sortViewModel.addSteps(
        SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState
        )
    )

}

fun checkGreaterOrLesser(num1: Int, num2: Int, order: String): Boolean {
    return if (order == "Ascending") {
        num1 > num2
    } else
        num1 < num2
}

fun generateRandomInput(sortViewModel: SortViewModel) {
    sortViewModel.emptySteps()
    sortViewModel.inputArray.forEachIndexed { index, _ ->
        val random = Random.nextInt(0, 100)
        sortViewModel.updateInputArray(index = index, newNum = random)
    }
}