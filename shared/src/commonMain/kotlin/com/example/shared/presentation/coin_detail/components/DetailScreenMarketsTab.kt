package com.example.shared.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinMarketsViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinTickerViewModel

@Composable
fun DetailScreenMarketsTab(
    coinTickerViewModel: CoinTickerViewModel = hiltViewModel(),
    coin: CoinMarketsViewModel = hiltViewModel()

) {
    val coinDetail = coin.state.value
    val coinTickerState = coinTickerViewModel.state.value

    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            coinDetail.coinMarkets?.let { coin ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(coin) { market ->
                        CoinMarketsItem(market = market)
                    }
                }
            }
        }
    }
}