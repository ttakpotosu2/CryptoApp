package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinMarketsViewModel

@Composable
fun DetailScreenMarketsTab(
    coin: CoinMarketsViewModel = hiltViewModel()
) {
    val coinDetail = coin.state.value

    Box(modifier = Modifier.fillMaxWidth()) {
        coinDetail.coinMarkets?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(coin) { market ->
                    CoinMarketsItem(market = market)
                    Divider(thickness = Dp.Hairline)
                }
            }
        }
    }
}