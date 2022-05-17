package com.example.dsalgoapp.ui.screens.algo_detail

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
import com.example.dsalgoapp.data.DsAlgoDetailsItem
import com.example.dsalgoapp.navigation.Screen

@Composable
fun AlgoDetailScreen(
    navController: NavController,
    algoDetailsViewModel: AlgoDetailsViewModel = hiltViewModel()
) {

    Scaffold(content = {

        LazyColumn(
            contentPadding = PaddingValues(all = Dp(10f)),
            verticalArrangement = Arrangement.spacedBy(Dp(10f))
        ) {
            items(count = algoDetailsViewModel.details.items.size) { index ->
                AlgoListItem(
                    item = algoDetailsViewModel.details.items[index],
                    navController = navController
                )
            }
        }
    })

}

@Composable
fun AlgoListItem(item: DsAlgoDetailsItem, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Black.copy(alpha = ContentAlpha.medium),
        shape = RoundedCornerShape(size = Dp(10f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dp(10f)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Dp(10f)))
            Text(
                text = item.description,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Dp(10f)))
            Button(
                onClick = {
                    if (item.id.contains("Search")) {
                        navController.navigate(Screen.Search.passSearchDetails(searchId = item.id))
                    } else if (item.id.contains("Sort")) {
                        navController.navigate(Screen.Sort.passSortDetails(sortId = item.id))
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Visualize")
            }
            Spacer(modifier = Modifier.height(Dp(10f)))
        }
    }
}