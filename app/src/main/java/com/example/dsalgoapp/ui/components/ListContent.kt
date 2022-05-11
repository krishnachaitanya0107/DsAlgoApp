package com.example.dsalgoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dsalgoapp.data.ListItem2

@Composable
fun ListContent(
    item: ListItem2,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(size = Dp(10f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = Dp(10f)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.name,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = item.image),
                    contentDescription = item.name
                )

            }

        }
    }

}