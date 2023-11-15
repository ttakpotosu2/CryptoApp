package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinDetailViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinTickerViewModel

@Composable
fun DetailScreenAboutTab(
    coinTickerViewModel: CoinTickerViewModel = hiltViewModel(),
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    val coinTickerState = coinTickerViewModel.state.value
    val coinDetailState = coinDetailViewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val coinTicker = coinTickerState.coinTicker
        val coinDetail = coinDetailState.coin

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Blue)
                .padding(12.dp)
        ) {
            MarketsInfo(label = "Markets", data = coinTicker?.quotes?.usd?.marketCap.toString())
            Spacer(modifier = Modifier.height(6.dp))
            MarketsInfo(label = "Total Supply", data = coinTicker?.totalSupply.toString())
            Spacer(modifier = Modifier.height(6.dp))
            MarketsInfo(label = "In Circulation", data = coinTicker?.circulatingSupply.toString())
            Spacer(modifier = Modifier.height(6.dp))
            MarketsInfo(label = "Total volumes", data = coinTicker?.maxSupply.toString())
        }
        if (coinDetail != null) {
            coinDetail.description?.let {
                Text(
                    text = it, color = Color.Black
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp)
        ) {
            if (coinDetail != null) {
                items(coinDetail.team){
                    TeamListItem(teamMember = it)
                }
            }
        }
    }
}