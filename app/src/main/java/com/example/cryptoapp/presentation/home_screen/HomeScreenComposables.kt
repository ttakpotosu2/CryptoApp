package com.example.cryptoapp.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.presentation.ui.theme.Monorama

@Composable
fun FavouritesCard(
    title: String,
    detail: String,
    subDetail: String
) {
    Column(
        modifier = Modifier
            .border(width = 2.dp, color = Color.LightGray)
            .width(180.dp)
            .height(180.dp)
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontFamily = Monorama
            )
        )
        Text(
            text = detail,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 38.sp,
                fontFamily = Monorama
            )
        )
        Text(
            text = subDetail,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontFamily = Monorama
            )
        )
    }
}

data class FavouritesInfo(
    val title: String,
    val detail: String,
    val subDetail: String
)

val favouritesInfo = listOf(
    FavouritesInfo(
        detail = "210K",
        title = "Art Blocks",
        subDetail = "35.k Owners"
    ),
    FavouritesInfo(
        title = "Floor Price",
        detail = "---",
        subDetail = "N/A"
    ),
    FavouritesInfo(
        title = "24H Volume",
        detail = "196 ETH",
        subDetail = "-0.11%"
    ),
    FavouritesInfo(
        title = "Today's Sellers",
        detail = "156 NFT",
        subDetail = "-1.4 ETH per NFT"
    )
)