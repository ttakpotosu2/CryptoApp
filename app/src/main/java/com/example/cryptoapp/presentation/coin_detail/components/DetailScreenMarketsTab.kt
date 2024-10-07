package com.example.cryptoapp.presentation.coin_detail.components

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
import com.example.shared.presentation.viewmodels.CoinMarketsViewModel
import com.example.shared.presentation.viewmodels.CoinTickerViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DetailScreenMarketsTab(
    coinTickerViewModel: CoinTickerViewModel = koinViewModel(),
    coin: CoinMarketsViewModel = koinViewModel()

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