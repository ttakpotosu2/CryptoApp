package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinDetailViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinEventsViewModel

@Composable
fun DetailScreenEventsTab(
    coinEventsViewModel: CoinEventsViewModel = hiltViewModel()
) {
    val coinDetail = coinEventsViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        coinDetail.coinEvents?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            )  {
                items(coin){event ->
                    CoinEventItem(event = event)
                }
            }
        }
    }
}