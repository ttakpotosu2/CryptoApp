package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import co.yml.charts.common.utils.DataUtils
import com.example.cryptoapp.presentation.coin_detail.CoinDetailViewModel
import com.example.cryptoapp.presentation.coin_detail.CoinTickerViewModel
import com.example.cryptoapp.presentation.coin_detail.components.ycharts.SingleLineChart

@Composable
fun DetailScreenPriceTab(
    coinTickerViewModel: CoinTickerViewModel = hiltViewModel(),
) {
    val coinTickerState = coinTickerViewModel.state.value
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xff2C2A2A))
            .padding(12.dp)
    ) {
        val coinTicker = coinTickerState.coinTicker
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            val price = coinTicker?.quotes?.usd?.price
            val formattedPrice = String.format("%.2f", price).replace(",", ".")
            Text(
                text = "$ $formattedPrice",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.width(6.dp))
            val figure = coinTicker?.quotes?.usd?.volume24hChange24h
            if (figure != null) {
                Text(
                    text = "${figure}%",
                    style = TextStyle(
                        color = if (figure < 0) Color.Red else Color.Green,
                        fontSize = 18.sp
                    )
                )
            }
        }
        val pointsData = DataUtils.getLineChartData(listSize = 15, maxRange = 300)

        SingleLineChart(
            pointData = pointsData
        )
        MarketsInfo(label = "Markets", data = coinTicker?.quotes?.usd?.marketCap.toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "Total Supply", data = coinTicker?.totalSupply.toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "In Circulation", data = coinTicker?.circulatingSupply.toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "Total volumes", data = coinTicker?.maxSupply.toString())
    }
}