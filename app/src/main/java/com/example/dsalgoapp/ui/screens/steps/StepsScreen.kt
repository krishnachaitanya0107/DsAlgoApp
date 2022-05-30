package com.example.dsalgoapp.ui.screens.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dsalgoapp.data.SortingStep
import com.example.dsalgoapp.ui.theme.Gray
import com.example.dsalgoapp.ui.theme.Green
import com.example.dsalgoapp.ui.theme.Red

@Composable
fun StepsScreen(
    navController: NavController,
    stepsViewModel: StepsViewModel = hiltViewModel()
) {
    generateSteps(stepsViewModel = stepsViewModel)
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            TitleContent(stepsViewModel = stepsViewModel)
            StepsContent(stepsViewModel = stepsViewModel)
        }
    }
}

@Composable
fun TitleContent(stepsViewModel: StepsViewModel) {
    lateinit var title: String
    lateinit var subtitle: String

    if (stepsViewModel.stepsType.contains("Search")) {
        title = "Number to search :"
        subtitle = stepsViewModel.numberToSearch
    } else if (stepsViewModel.stepsType.contains("Sort")) {
        title = "Sort Order :"
        subtitle = stepsViewModel.sortOrder
    }

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
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {

            Text("Input Array :")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                stepsViewModel.inputArray.forEachIndexed { index, num ->
                    Text(text = num.toString())
                    Spacer(modifier = Modifier.width(Dp(10f)))
                }
            }

            Text(text = title)

            Text(text = subtitle)
        }
    }

}

@Composable
fun StepsContent(stepsViewModel: StepsViewModel) {

    if (stepsViewModel.stepsType.contains("Search")) {

        LazyColumn(
            contentPadding = PaddingValues(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            items(count = stepsViewModel.steps.size) { item ->

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
                            stepsViewModel.inputArray.forEachIndexed { index, num ->
                                Box(
                                    modifier =
                                    Modifier
                                        .background(
                                            color = getColorState(item, index, stepsViewModel),
                                            shape = RoundedCornerShape(size = Dp(1f))
                                        )
                                ) {
                                    Text(text = num.toString(), modifier = Modifier.padding(Dp(3f)))
                                }
                                Spacer(modifier = Modifier.width(Dp(10f)))
                            }
                        }
                        Text(text = stepsViewModel.steps[item])
                    }
                }
                Spacer(modifier = Modifier.height(Dp(10f)))

            }
        }
    } else if (stepsViewModel.stepsType.contains("Sort")) {

        LazyColumn(
            contentPadding = PaddingValues(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            items(count = stepsViewModel.sortSteps.size) { item ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = Dp(10f)),
                        verticalArrangement = Arrangement.spacedBy(Dp(10f))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            stepsViewModel.sortSteps[item].arrayState.forEachIndexed { index, num ->
                                Box(
                                    modifier =
                                    Modifier
                                        .background(
                                            color = getColorState(
                                                item,
                                                index,
                                                stepsViewModel
                                            ),
                                            shape = RoundedCornerShape(size = Dp(1f))
                                        )
                                ) {
                                    Text(
                                        text = stepsViewModel.inputArray[num.digitToInt()].toString(),
                                        modifier = Modifier.padding(Dp(3f))
                                    )
                                }
                                Spacer(modifier = Modifier.width(Dp(10f)))
                            }
                        }
                        Text(text = stepsViewModel.sortSteps[item].step)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "No of Comparisons : "+stepsViewModel.sortSteps[item].noOfComparisons)
                            Text(text = "No of Swaps : "+stepsViewModel.sortSteps[item].noOfSwaps)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(Dp(10f)))

            }
        }
    }
}


fun getColorState(step: Int, index: Int, stepsViewModel: StepsViewModel): Color {
    if (stepsViewModel.stepsType.contains("linear")) {
        if (stepsViewModel.numberFound.value && stepsViewModel.steps.size == step + 1) {
            return if (index < stepsViewModel.steps.size - 1) {
                Gray
            } else if (index == stepsViewModel.steps.size - 1) {
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
    } else if (stepsViewModel.stepsType.contains("binary")) {
        if (step == 0) {
            return Color.Transparent
        }
        if (step >= stepsViewModel.binarySearchStates.size) {
            return Gray
        }

        val curState = stepsViewModel.binarySearchStates[step]
        if (stepsViewModel.numberFound.value) {
            if (step == stepsViewModel.binarySearchStates.size - 1 && index == curState[1]) {
                return Green
            }
        }
        return if (index == curState[1]) {
            Red
        } else if (index < curState[0] || index > curState[2]) {
            Gray
        } else {
            Color.Transparent
        }

    } else if (stepsViewModel.stepsType.contains("bubble") || stepsViewModel.stepsType.contains("selection")) {
        val arrSize = stepsViewModel.sortSteps[step].modifiedArrSize
        val curPosition = stepsViewModel.sortSteps[step].currentComparisons

        return if (curPosition.contains(index))
            Gray
        else if (index > arrSize)
            Green
        else
            Color.Transparent
    } else if (stepsViewModel.stepsType.contains("insertion")) {
        val curPosition = stepsViewModel.sortSteps[step].currentComparisons

        return if (curPosition.contains(index))
            Gray
        else if (step == stepsViewModel.sortSteps.size - 1)
            Green
        else
            Color.Transparent
    } else if (stepsViewModel.stepsType.contains("quick")) {
        return Color.Transparent
    } else {
        return Color.Transparent
    }
}


fun generateSteps(stepsViewModel: StepsViewModel) {
    if (stepsViewModel.stepsType.contains("linear")) {
        generateLinearSearchSteps(stepsViewModel = stepsViewModel)
    } else if (stepsViewModel.stepsType.contains("binary")) {
        generateBinarySearchSteps(stepsViewModel = stepsViewModel)
    } else if (stepsViewModel.stepsType.contains("bubble")) {
        generateBubbleSortSteps(stepsViewModel = stepsViewModel)
    } else if (stepsViewModel.stepsType.contains("selection")) {
        generateSelectionSortSteps(stepsViewModel = stepsViewModel)
    } else if (stepsViewModel.stepsType.contains("insertion")) {
        generateInsertionSortSteps(stepsViewModel = stepsViewModel)
    } else if (stepsViewModel.stepsType.contains("quick")) {
        //generateQuickSortSteps(sortViewModel = sortViewModel)
    }

}

fun generateLinearSearchSteps(stepsViewModel: StepsViewModel) {
    reset(stepsViewModel)

    val arr = stepsViewModel.inputArray
    val searchFor = stepsViewModel.numberToSearch.toInt()

    for (i in arr.indices) {
        if (searchFor == arr[i]) {
            stepsViewModel.addSteps("Element $searchFor found at index $i")
            stepsViewModel.updateNumberFound(true)
            break
        } else {
            stepsViewModel.addSteps("Comparing element at index $i \n${arr[i]} with $searchFor")
        }
    }
    if (!stepsViewModel.numberFound.value) {
        stepsViewModel.addSteps("Element $searchFor is not present in the array")
    }
}

fun generateBinarySearchSteps(stepsViewModel: StepsViewModel) {
    reset(stepsViewModel)

    val arr = stepsViewModel.inputArray
    val searchFor = stepsViewModel.numberToSearch.toInt()

    var start = 0
    var end = arr.size - 1
    var mid: Int = ((start + end) / 2)

    stepsViewModel.addSteps("Sorted input array\nInitializing values... \nstart = $start \nend = $end \nmid = (start+end)/2 = $mid")
    stepsViewModel.addBinarySearchStates(listOf(start, mid, end))

    while (start <= end) {
        if (arr[mid] == searchFor) {
            stepsViewModel.addSteps("Element $searchFor found at index $mid")
            stepsViewModel.addBinarySearchStates(listOf(start, mid, end))
            stepsViewModel.updateNumberFound(true)
            break
        } else {
            if (arr[mid] < searchFor) {
                stepsViewModel.addSteps("Element $searchFor is greater than the mid element ${arr[mid]} \nUpdating start = ${mid + 1} \nUpdating mid = ${(mid + 1 + end) / 2} \nend = $end")
                start = mid + 1
                stepsViewModel.addBinarySearchStates(listOf(start, mid, end))
            } else if (arr[mid] > searchFor) {
                stepsViewModel.addSteps("Element $searchFor is lesser than the mid element ${arr[mid]} \nUpdating end = ${mid - 1} \nUpdating mid = ${(mid - 1 + start) / 2} \nstart = $start")
                end = mid - 1
                stepsViewModel.addBinarySearchStates(listOf(start, mid, end))
            }
            mid = (start + end) / 2
        }
    }

    if (!stepsViewModel.numberFound.value) {
        stepsViewModel.addSteps("Element $searchFor is not present in the array")
    }
}

fun generateBubbleSortSteps(stepsViewModel: StepsViewModel) {
    stepsViewModel.emptySortSteps()
    val numbers = arrayListOf<Int>()
    for (i in stepsViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (stepsViewModel.sortOrder == "Ascending") "greater" else "less"

    for (pass in 0 until size) {
        for (currentPosition in 0 until (size - pass)) {
            noOfComparisons++
            if (checkGreaterOrLesser(
                    num1 = numbers[currentPosition],
                    num2 = numbers[currentPosition + 1],
                    order = stepsViewModel.sortOrder
                )
            ) {
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[currentPosition]} is $greaterOrLesser than ${numbers[currentPosition + 1]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                )
                val temp = numbers[currentPosition]
                numbers[currentPosition] = numbers[currentPosition + 1]
                numbers[currentPosition + 1] = temp
                noOfSwaps++
                arrayState =
                    arrayState.substring(0, currentPosition) + arrayState[currentPosition + 1] +
                            arrayState[currentPosition] + arrayState.substring(currentPosition + 2)
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "Swapping ${numbers[currentPosition + 1]} with ${numbers[currentPosition]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                )
            } else {
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[currentPosition]} is not $greaterOrLesser than ${numbers[currentPosition + 1]}\nNo Swapping done",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(currentPosition, currentPosition + 1),
                        modifiedArrSize = size - pass,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                )
            }
        }
    }
    stepsViewModel.addSteps(
        SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            modifiedArrSize = -1,
            noOfComparisons = noOfComparisons,
            noOfSwaps = noOfSwaps
        )
    )

}

fun generateSelectionSortSteps(stepsViewModel: StepsViewModel) {
    stepsViewModel.emptySortSteps()
    val numbers = arrayListOf<Int>()
    for (i in stepsViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (stepsViewModel.sortOrder == "Ascending") "greater" else "less"
    val maxOrMin = if (stepsViewModel.sortOrder == "Ascending") "max" else "min"

    for (i in size downTo 1) {
        var num = i
        for (j in 0 until i) {
            noOfComparisons++
            if (checkGreaterOrLesser(
                    numbers[j],
                    numbers[num],
                    stepsViewModel.sortOrder
                )
            ) {
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[j]} is $greaterOrLesser than ${numbers[num]}\nSetting new $maxOrMin index to $j",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, num),
                        modifiedArrSize = i,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                )
                num = j
            } else {
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[j]} is not $greaterOrLesser than ${numbers[num]}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, num),
                        modifiedArrSize = i,
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                )
            }
        }
        if (i != num) {
            val temp = numbers[i]
            numbers[i] = numbers[num]
            numbers[num] = temp
            noOfSwaps++
            arrayState =
                arrayState.substring(0, num) + arrayState[i] + arrayState.substring(num + 1, i) +
                        arrayState[num] + arrayState.substring(i + 1)
            stepsViewModel.addSteps(
                SortingStep(
                    step = "Swapping ${numbers[num]} with ${numbers[i]}",
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(i, num),
                    modifiedArrSize = i,
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
            )
        } else {
            stepsViewModel.addSteps(
                SortingStep(
                    step = "No Swapping performed",
                    arrayState = arrayState,
                    modifiedArrSize = i,
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
            )
        }
    }

    stepsViewModel.addSteps(
        SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            modifiedArrSize = -1,
            noOfComparisons = noOfComparisons,
            noOfSwaps = noOfSwaps
        )
    )

}

fun generateInsertionSortSteps(stepsViewModel: StepsViewModel) {
    stepsViewModel.emptySortSteps()
    val numbers = arrayListOf<Int>()
    for (i in stepsViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"
    var noOfComparisons = 0
    var noOfSwaps = 0

    val greaterOrLesser = if (stepsViewModel.sortOrder == "Ascending") "greater" else "less"

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
                    stepsViewModel.sortOrder
                )
            ) {
                arrayState =
                    arrayState.substring(0, j + 1) + arrayState[j] + arrayState.substring(j + 2)
                noOfSwaps++
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "${numbers[j]} is $greaterOrLesser than $num\nShifting ${numbers[j]} to the right\nFinding position for $num",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j, j + 1),
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
                )
                numbers[j + 1] = numbers[j]
                hasMoved = true
            } else {
                arrayState = arrayState.substring(0, j + 1) + t + arrayState.substring(j + 2)
                noOfSwaps++
                stepsViewModel.addSteps(
                    SortingStep(
                        step = "Inserting $num at index ${j + 1}",
                        arrayState = arrayState,
                        currentComparisons = arrayListOf(j + 1),
                        noOfComparisons = noOfComparisons,
                        noOfSwaps = noOfSwaps
                    )
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
            stepsViewModel.addSteps(
                SortingStep(
                    step = "Inserting $num at index 0",
                    arrayState = arrayState,
                    currentComparisons = arrayListOf(0),
                    noOfComparisons = noOfComparisons,
                    noOfSwaps = noOfSwaps
                )
            )
        }

    }

    stepsViewModel.addSteps(
        SortingStep(
            step = "Array is sorted Successfully",
            arrayState = arrayState,
            noOfComparisons = noOfComparisons,
            noOfSwaps = noOfSwaps
        )
    )

}


/*fun generateQuickSortSteps(sortViewModel: SortViewModel){
    sortViewModel.emptySteps()
    val numbers = arrayListOf<Int>()
    for (i in sortViewModel.inputArray) {
        numbers.add(i)
    }
    val size = numbers.size - 1
    var arrayState = "01234567"

    val greaterOrLesser = if (sortViewModel.selectedOption == "Ascending") "greater" else "less"

    quickSort(numbers,0,size,arrayState,greaterOrLesser)
}

fun quickSort(input:ArrayList<Int>,left:Int,right:Int,arrayState:String,greaterOrLesser:String){
    if(right<=left){
        return
    }

    val pivot=(left+right)/2

    var i=left
    var j=right

    while(i<=j){
        while(input[i]<pivot)
            i++
        while (input[j]>pivot)
            j--

        if(i<=j){
            val temp=input[i]
            input[i]=input[j]
            input[j]=temp
            i++
            j--
        }
    }

    quickSort(input,left,i-1,arrayState,greaterOrLesser)
    quickSort(input,i,right,arrayState,greaterOrLesser)

}*/

fun checkGreaterOrLesser(num1: Int, num2: Int, order: String): Boolean {
    return if (order == "Ascending") {
        num1 > num2
    } else
        num1 < num2
}

fun reset(stepsViewModel: StepsViewModel) {
    stepsViewModel.emptySteps()
    stepsViewModel.emptyBinarySearchStates()
    stepsViewModel.updateNumberFound(false)
}
