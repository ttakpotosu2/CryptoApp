package com.example.cryptoapp.presentation.coins_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.coin_detail.components.CoinListItem
import com.valentinilk.shimmer.shimmer
import androidx.compose.ui.text.TextStyle
import com.example.cryptoapp.presentation.ui.theme.Kadwa

@Composable
fun CoinsScreen(
    viewModel: CoinsListViewModel = hiltViewModel(),
    toCoinDetailScreen: (String) -> Unit
) {
    val state = viewModel.state.value

    if (state.coins.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1C1B1F))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color.White)
                ) {
                    Text(
                        text = "Coins",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 48.sp,
                            fontFamily = Kadwa
                        ),
                        modifier = Modifier
                            .padding(top = 90.dp)
                            .padding(8.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xff1E60DF))
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(4f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.coins) { coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClick = { toCoinDetailScreen(coin.id) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1C1B1F))
                .shimmer(),
            contentAlignment = Alignment.Center
        ) {
            // Detail out shapes for loading shimmer
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.LightGray)
            )
        }
    }
}

