package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.coin_detail.CoinDetailViewModel

@Composable
fun DetailScreenAboutTab(
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    val coinDetail = coinDetailViewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        coinDetail.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    coin.description?.let { Text(text = it) }
                    Divider(thickness = Dp.Hairline)
                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider(thickness = Dp.Hairline)
                }
            }
        }
    }
}