package com.example.dsalgoapp.ui.screens.real_time

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.data.SortingStep
import com.example.dsalgoapp.ui.screens.steps.checkGreaterOrLesser
import com.example.dsalgoapp.ui.theme.*
import com.example.dsalgoapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RealTimeScreen(
    navController: NavController,
    realTimeViewModel: RealTimeViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.Center
        ) {
            RealTimeContent(realTimeViewModel = realTimeViewModel)
            CustomRadioGroup(realTimeViewModel = realTimeViewModel)
        }
    }
}

@Composable
fun RealTimeContent(realTimeViewModel: RealTimeViewModel) {
    realTimeViewModel.initArray()
    if (realTimeViewModel.stepsType.contains("Search")) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.topAppBarBackgroundColor),
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
                    realTimeViewModel.inputArray.forEachIndexed { index, num ->
                        Box(
                            modifier =
                            Modifier
                                .background(
                                    color = getColorState(index, realTimeViewModel),
                                    shape = RoundedCornerShape(size = Dp(1f))
                                )
                        ) {
                            Text(
                                text = num.toString(),
                                modifier = Modifier.padding(Dp(3f)),
                                color = MaterialTheme.colors.titleColor
                            )
                        }
                        Spacer(modifier = Modifier.width(Dp(10f)))
                    }
                }
                Text(
                    text = realTimeViewModel.currStep.addEmptyLines(4),
                    color = MaterialTheme.colors.titleColor,
                    maxLines = 5
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(
                        onClick = {
                            generateSteps(realTimeViewModel = realTimeViewModel)
                        },
                        colors = if (!realTimeViewModel.isVisualizationRunning) {
                            ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        } else
                            ButtonDefaults.buttonColors(
                                backgroundColor = Gray,
                                contentColor = Color.White
                            )
                    ) {
                        Text(text = "Start")
                    }

                    Button(
                        onClick = {
                            if (!realTimeViewModel.isVisualizationRunning) {
                                reset(realTimeViewModel = realTimeViewModel)
                            }
                        },
                        colors = if (!realTimeViewModel.isVisualizationRunning) {
                            ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        } else
                            ButtonDefaults.buttonColors(
                                backgroundColor = Gray,
                                contentColor = Color.White
                            )
                    ) {
                        Text(text = "Reset")
                    }
                }
            }
        }
    } else if (realTimeViewModel.stepsType.contains("Sort")) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.topAppBarBackgroundColor),
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
                    realTimeViewModel.sortStep.arrayState.forEachIndexed { index, num ->
                        Box(
                            modifier =
                            Modifier
                                .background(
                                    color = getColorState(index, realTimeViewModel),
                                    shape = RoundedCornerShape(size = Dp(1f))
                                )
                        ) {
                            Text(
                                text = realTimeViewModel.inputArray[num.digitToInt()].toString(),
                                modifier = Modifier.padding(Dp(3f)),
                                color = MaterialTheme.colors.titleColor
                            )
                        }
                        Spacer(modifier = Modifier.width(Dp(10f)))
                    }
                }
                Text(
                    text = realTimeViewModel.sortStep.step.addEmptyLines(4),
                    color = MaterialTheme.colors.titleColor,
                    maxLines = 5
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "No of Comparisons : " + realTimeViewModel.sortStep.noOfComparisons,
                        color = MaterialTheme.colors.titleColor
                    )
                    Text(
                        text = "No of Swaps : " + realTimeViewModel.sortStep.noOfSwaps,
                        color = MaterialTheme.colors.titleColor
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(
                        onClick = {
                            if (!realTimeViewModel.isVisualizationRunning) {
                                realTimeViewModel.isVisualizationRunning = true
                                generateSteps(realTimeViewModel = realTimeViewModel)
                            }
                        },
                        colors = if (!realTimeViewModel.isVisualizationRunning) {
                            ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        } else
                            ButtonDefaults.buttonColors(
                                backgroundColor = Gray,
                                contentColor = Color.White
                            )
                    ) {
                        Text(text = "Start")
                    }

                    Button(
                        onClick = {
                            if (!realTimeViewModel.isVisualizationRunning) {
                                reset(realTimeViewModel = realTimeViewModel)
                            }
                        },
                        colors = if (!realTimeViewModel.isVisualizationRunning) {
                            ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                                contentColor = Color.White
                            )
                        } else
                            ButtonDefaults.buttonColors(
                                backgroundColor = Gray,
                                contentColor = Color.White
                            )
                    ) {
                        Text(text = "Reset")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(Dp(10f)))
    }
}


@Composable
fun CustomRadioGroup(realTimeViewModel: RealTimeViewModel) {

    val speeds = HashMap<String, Long>()

    speeds["Slow"] = 1500L
    speeds["Medium"] = 1200L
    speeds["Fast"] = 800L

    val onSelectionChange = { speed: Long ->
        realTimeViewModel.visualizationSpeed = speed
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        speeds.forEach { speed ->
            Text(text = speed.key, modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        size = Dp(10f),
                    ),
                )
                .clickable {
                    onSelectionChange(speed.value)
                }
                .background(
                    if (speed.value == realTimeViewModel.visualizationSpeed) {
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
fun getColorState(index: Int, realTimeViewModel: RealTimeViewModel): Color {
    if (realTimeViewModel.stepsType.contains("linear")) {
        val step = realTimeViewModel.linearSearchState
        val numberFoundIndex = realTimeViewModel.numberFoundIndex
        if (numberFoundIndex != -1) {
            return if (index < numberFoundIndex) {
                Gray
            } else if (index == numberFoundIndex) {
                MaterialTheme.colors.green
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
    } else if (realTimeViewModel.stepsType.contains("binary")) {
        val curState = realTimeViewModel.binarySearchStates
        val numberFoundIndex = realTimeViewModel.numberFoundIndex
        if (numberFoundIndex != -1) {
            if (index == numberFoundIndex) {
                return MaterialTheme.colors.green
            }
        }
        return if (index == curState[1]) {
            Red
        } else if (index < curState[0] || index > curState[2]) {
            Gray
        } else {
            Color.Transparent
        }
    } else if (realTimeViewModel.stepsType.contains("bubble") || realTimeViewModel.stepsType.contains(
            "selection"
        )
    ) {
        val arrSize = realTimeViewModel.sortStep.modifiedArrSize
        val curPosition = realTimeViewModel.sortStep.currentComparisons

        return if (curPosition.contains(index))
            Gray
        else if (index > arrSize)
            MaterialTheme.colors.green
        else
            Color.Transparent
    } else if (realTimeViewModel.stepsType.contains("insertion")) {
        val curPosition = realTimeViewModel.sortStep.currentComparisons

        return if (curPosition.contains(index))
            Gray
        else if (curPosition.contains(-1))
            MaterialTheme.colors.green
        else
            Color.Transparent
    }
    return Color.Transparent
}

fun String.addEmptyLines(lines: Int) = this + "\n".repeat(lines)

fun generateSteps(realTimeViewModel: RealTimeViewModel) {
    if (realTimeViewModel.stepsType.contains("linear")) {
        visualizeLinearSearch(realTimeViewModel = realTimeViewModel)
    } else if (realTimeViewModel.stepsType.contains("binary")) {
        visualizeBinarySearch(realTimeViewModel = realTimeViewModel)
    } else if (realTimeViewModel.stepsType.contains("bubble")) {
        visualizeBubbleSort(realTimeViewModel = realTimeViewModel)
    } else if (realTimeViewModel.stepsType.contains("selection")) {
        visualizeSelectionSort(realTimeViewModel = realTimeViewModel)
    } else if (realTimeViewModel.stepsType.contains("insertion")) {
        visualizeInsertionSort(realTimeViewModel = realTimeViewModel)
    } else if (realTimeViewModel.stepsType.contains("quick")) {
        //visualizeQuickSort(realTimeViewModel = realTimeViewModel)
        //generateQuickSortSteps(sortViewModel = sortViewModel)
    }

}

fun reset(realTimeViewModel: RealTimeViewModel) {
    if (realTimeViewModel.stepsType.contains("Search")) {
        realTimeViewModel.currStep = ""
        realTimeViewModel.linearSearchState = 0
        realTimeViewModel.numberFoundIndex = -1
        realTimeViewModel.binarySearchStates = listOf(-1, 8, 8)
    } else if (realTimeViewModel.stepsType.contains("Sort")) {
        realTimeViewModel.sortStep =
            SortingStep(step = "", arrayState = "01234567", modifiedArrSize = 8)
    }
}

fun visualizeLinearSearch(realTimeViewModel: RealTimeViewModel) {
    val arr = realTimeViewModel.inputArray
    val searchFor = realTimeViewModel.userInputNumberToSearch.toInt()

    CoroutineScope(Dispatchers.IO).launch {

        for (i in arr.indices) {
            if (searchFor == arr[i]) {
                delay(realTimeViewModel.visualizationSpeed)
                realTimeViewModel.currStep = "Element $searchFor found at index $i"
                realTimeViewModel.linearSearchState = i
                realTimeViewModel.numberFoundIndex = i
                break
            } else {
                delay(realTimeViewModel.visualizationSpeed)
                realTimeViewModel.currStep =
                    "Comparing element at index $i \n${arr[i]} with $searchFor"
                realTimeViewModel.linearSearchState = i
            }
        }
    }.invokeOnCompletion {
        if (realTimeViewModel.numberFoundIndex == -1) {
            realTimeViewModel.linearSearchState = arr.size
            realTimeViewModel.currStep = "Element $searchFor is not present in the array"
        }
        realTimeViewModel.isVisualizationRunning = false
    }

}

fun visualizeBinarySearch(realTimeViewModel: RealTimeViewModel) {
    val arr = realTimeViewModel.inputArray
    val searchFor = realTimeViewModel.userInputNumberToSearch.toInt()

    var start = 0
    var end = arr.size - 1
    var mid: Int = ((start + end) / 2)

    realTimeViewModel.currStep =
        "Sorted input array\nInitializing values... \nstart = $start \nend = $end \nmid = (start+end)/2 = $mid"


    CoroutineScope(Dispatchers.IO).launch {
        while (start <= end) {
            if (arr[mid] == searchFor) {
                delay(realTimeViewModel.visualizationSpeed)
                realTimeViewModel.currStep = "Element $searchFor found at index $mid"
                realTimeViewModel.addBinarySearchStates(listOf(start, mid, end))
                realTimeViewModel.numberFoundIndex = mid
                break
            } else {
                if (arr[mid] < searchFor) {
                    delay(realTimeViewModel.visualizationSpeed)
                    start = mid + 1
                    realTimeViewModel.currStep =
                        "Element $searchFor is greater than the mid element ${arr[mid]} \nUpdating start = $start \nUpdating mid = ${(start + end) / 2} \nend = $end"
                    realTimeViewModel.addBinarySearchStates(listOf(start, mid, end))
                } else if (arr[mid] > searchFor) {
                    delay(realTimeViewModel.visualizationSpeed)
                    end = mid - 1
                    realTimeViewModel.currStep =
                        "Element $searchFor is lesser than the mid element ${arr[mid]} \nUpdating end = $end \nUpdating mid = ${(end + start) / 2} \nstart = $start"
                    realTimeViewModel.addBinarySearchStates(listOf(start, mid, end))
                }
                mid = (start + end) / 2
            }
        }
    }.invokeOnCompletion {
        if (realTimeViewModel.numberFoundIndex == -1) {
            realTimeViewModel.addBinarySearchStates(listOf(8, 8, 8))
            realTimeViewModel.currStep = "Element $searchFor is not present in the array"
        }
        realTimeViewModel.isVisualizationRunning = false
    }
}

fun visualizeBubbleSort(realTimeViewModel: RealTimeViewModel) {

    val numbers = arrayListOf<Int>()
    for (i in realTimeViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (realTimeViewModel.sortOrder == Constants.ASCENDING) Constants.GREATER else Constants.LESSER

    CoroutineScope(Dispatchers.IO).launch {
        for (pass in 0 until size) {
            for (currentPosition in 0 until (size - pass)) {
                noOfComparisons++
                if (checkGreaterOrLesser(
                        num1 = numbers[currentPosition],
                        num2 = numbers[currentPosition + 1],
                        order = realTimeViewModel.sortOrder
                    )
                ) {

                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "${numbers[currentPosition]} is $greaterOrLesser than ${numbers[currentPosition + 1]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )

                    val temp = numbers[currentPosition]
                    numbers[currentPosition] = numbers[currentPosition + 1]
                    numbers[currentPosition + 1] = temp
                    noOfSwaps++
                    arrayState =
                        arrayState.substring(0, currentPosition) + arrayState[currentPosition + 1] +
                                arrayState[currentPosition] + arrayState.substring(currentPosition + 2)

                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "Swapping ${numbers[currentPosition + 1]} with ${numbers[currentPosition]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                } else {

                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "${numbers[currentPosition]} is not $greaterOrLesser than ${numbers[currentPosition + 1]}\nNo Swapping done",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                }
            }
        }
    }.invokeOnCompletion {
        realTimeViewModel.sortStep = SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            modifiedArrSize = -1,
            noOfComparisons = noOfComparisons,
            noOfSwaps = noOfSwaps
        )
        realTimeViewModel.isVisualizationRunning = false
    }

}


fun visualizeSelectionSort(realTimeViewModel: RealTimeViewModel) {
    val numbers = arrayListOf<Int>()
    for (i in realTimeViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (realTimeViewModel.sortOrder == Constants.ASCENDING) Constants.GREATER else Constants.LESSER
    val maxOrMin = if (realTimeViewModel.sortOrder == Constants.ASCENDING) "max" else "min"

    CoroutineScope(Dispatchers.IO).launch {

        for (i in size downTo 1) {
            var num = i
            for (j in 0 until i) {
                noOfComparisons++
                if (checkGreaterOrLesser(
                        numbers[j],
                        numbers[num],
                        realTimeViewModel.sortOrder
                    )
                ) {

                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "${numbers[j]} is $greaterOrLesser than ${numbers[num]}\nSetting new $maxOrMin index to $j",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, num),
                        modifiedArrSize = i,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )

                    num = j
                } else {
                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "${numbers[j]} is not $greaterOrLesser than ${numbers[num]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, num),
                        modifiedArrSize = i,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )

                }
            }
            if (i != num) {
                val temp = numbers[i]
                numbers[i] = numbers[num]
                numbers[num] = temp
                noOfSwaps++
                arrayState =
                    arrayState.substring(0, num) + arrayState[i] + arrayState.substring(
                        num + 1,
                        i
                    ) +
                            arrayState[num] + arrayState.substring(i + 1)

                delay(realTimeViewModel.visualizationSpeed)
                realTimeViewModel.sortStep = SortingStep(
                    step = "Swapping ${numbers[num]} with ${numbers[i]}",
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(i, num),
                    modifiedArrSize = i,
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )

            } else {
                delay(realTimeViewModel.visualizationSpeed)
                realTimeViewModel.sortStep = SortingStep(
                    step = "No Swapping performed",
                    arrayState = arrayState,
                    modifiedArrSize = i,
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
            }
        }
    }.invokeOnCompletion {
        realTimeViewModel.sortStep = SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            modifiedArrSize = -1,
            noOfComparisons = noOfComparisons,
            noOfSwaps = noOfSwaps
        )
        realTimeViewModel.isVisualizationRunning = false
    }
}

fun visualizeInsertionSort(realTimeViewModel: RealTimeViewModel) {
    val numbers = arrayListOf<Int>()
    for (i in realTimeViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (realTimeViewModel.sortOrder == Constants.ASCENDING) Constants.GREATER else Constants.LESSER

    CoroutineScope(Dispatchers.IO).launch {
        for (i in 1..size) {
            val num = numbers[i]
            var hasMoved = false
            var notPlaced = true
            val t = arrayState[i]

            for (j in i - 1 downTo 0) {
                noOfComparisons++
                if (checkGreaterOrLesser(
                        numbers[j],
                        num,
                        realTimeViewModel.sortOrder
                    )
                ) {
                    arrayState =
                        arrayState.substring(0, j + 1) + arrayState[j] + arrayState.substring(j + 2)
                    noOfSwaps++

                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "${numbers[j]} is $greaterOrLesser than $num\nShifting ${numbers[j]} to the right\nFinding position for $num",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, j + 1),
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )

                    numbers[j + 1] = numbers[j]
                    hasMoved = true
                } else {
                    arrayState = arrayState.substring(0, j + 1) + t + arrayState.substring(j + 2)
                    noOfSwaps++

                    delay(realTimeViewModel.visualizationSpeed)
                    realTimeViewModel.sortStep = SortingStep(
                        step = "Inserting $num at index ${j + 1}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j + 1),
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                    numbers[j + 1] = num
                    notPlaced = false
                    break
                }
            }

            if (hasMoved && notPlaced) {
                numbers[0] = num
                noOfSwaps++
                arrayState = t + arrayState.substring(1)

                delay(realTimeViewModel.visualizationSpeed)
                realTimeViewModel.sortStep = SortingStep(
                    step = "Inserting $num at index 0",
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(0),
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
            }

        }
    }.invokeOnCompletion {
        realTimeViewModel.sortStep = SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            currentComparisons = arrayListOf(-1),
            noOfComparisons = noOfComparisons,
            noOfSwaps = noOfSwaps
        )
        realTimeViewModel.isVisualizationRunning = false
    }

}

/*suspend fun visualizeQuickSort(realTimeViewModel: RealTimeViewModel) {

    val numbers = arrayListOf<Int>()
    for (i in realTimeViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (realTimeViewModel.sortOrder == Constants.ASCENDING) Constants.GREATER else Constants.LESSER

    val stack = mutableListOf<Pair<Int, Int>>()
    stack.add(0 to size)

    while (stack.isNotEmpty()) {
        val (low, high) = stack.removeAt(stack.size - 1)
        var i = low
        var j = high
        val pivot = numbers[(low + high) / 2]

        while (i <= j) {
            while (numbers[i] < pivot) i++
            while (numbers[j] > pivot) j--
            if (i <= j) {
                val temp = numbers[i]
                numbers[i] = numbers[j]
                numbers[j] = temp

                noOfComparisons++
                noOfSwaps++

                val step = "Swapping ${numbers[j]} with ${numbers[i]}"
                realTimeViewModel.sortStep = SortingStep(
                    step = step,
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(i, j),
                    modifiedArrSize = size,
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
                delay(realTimeViewModel.visualizationSpeed)

                i++
                j--
            } else {
                noOfComparisons++

                val step = "Not Swapping anything"
                realTimeViewModel.sortStep = SortingStep(
                    step = step,
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(i, j),
                    modifiedArrSize = size,
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
                delay(realTimeViewModel.visualizationSpeed)
            }
        }

        if (low < j) stack.add(low to j)
        if (i < high) stack.add(i to high)
    }
}*/
