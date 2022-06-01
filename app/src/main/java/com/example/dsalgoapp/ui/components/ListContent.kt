package com.example.dsalgoapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dsalgoapp.data.DsAlgoSubItem
import com.example.dsalgoapp.ui.theme.titleColor
import com.example.dsalgoapp.ui.theme.topAppBarBackgroundColor

@Composable
fun ListContent(
    item: DsAlgoSubItem,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.topAppBarBackgroundColor,
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
                    color = MaterialTheme.colors.titleColor,
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