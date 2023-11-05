package com.example.cryptoapp.presentation.coins_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.domain.model.Coins

@Composable
fun CoinsScreen(
    viewModel: CoinsListViewModel = hiltViewModel(),
    toCoinDetailScreen: (String) -> Unit
) {
    val state = viewModel.state.value
    var selectedItem by remember {
        mutableStateOf("Home")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
    ) {
        Text(
            text = "Coins",
            color = Color.White,
            fontSize = 64.sp,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(state.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = { toCoinDetailScreen(coin.id) }
                )
                Spacer(modifier = Modifier.height(16.dp))
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
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(CenterHorizontally),
                color = Color.White
            )
        }
    }
}

@Composable
fun CoinListItem(
    coin: Coins,
    onItemClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(Color(0xff353538))
            .padding(6.dp)
            .clickable { onItemClick() }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "#"+coin.rank.toString(),
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff434346))
                    .padding(horizontal = 4.dp),
                fontSize = 14.sp
            )
            Text(
                text = coin.symbol,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff434346))
                    .padding(horizontal = 6.dp),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (coin.isActive) "active" else "inactive",
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(if (coin.isActive) 0xff294D2D else 0xff4D2929))
                    .padding(horizontal = 6.dp),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = coin.name,
            color = Color.White,
            fontSize = 40.sp
        )
    }
}