package com.example.cryptoapp.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.common.extensions.isNotNull
import co.yml.charts.common.utils.DataUtils
import coil.compose.SubcomposeAsyncImage
import com.example.cryptoapp.presentation.coin_detail.components.CoinDetailTabs
import com.example.cryptoapp.presentation.coin_detail.components.CoinTag
import com.example.cryptoapp.presentation.coin_detail.components.charts.SingleLineChart
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinDetailViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinTickerViewModel
import com.example.cryptoapp.presentation.ui.theme.Kadwa
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    coinTickerViewModel: CoinTickerViewModel = hiltViewModel(),
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    val coinTickerState = coinTickerViewModel.state.value
    val coinDetailState = coinDetailViewModel.state.value
    val pointsData = DataUtils.getLineChartData(listSize = 9, maxRange = 300)

    if (coinDetailState.coin.isNotNull()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1C1B1F))
                .padding(8.dp)
        ) {
            coinDetailState.coin?.let { coin ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val coinTicker = coinTickerState.coinTicker
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(White)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),

                            ) {
                            SubcomposeAsyncImage(
                                model = coin.logo,
                                loading = { CircularProgressIndicator(color = White) },
                                contentDescription = "coin logo",
                                modifier = Modifier.size(50.dp),
                                alignment = Center,
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "#" + coin.rank.toString(),
                                color = White,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xff434346))
                                    .padding(horizontal = 4.dp),
                                fontSize = 14.sp
                            )
                            Text(
                                text = if (coin.isActive == true) "active" else "inactive",
                                color = White,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(if (coin.isActive == true) 0xff294D2D else 0xff4D2929))
                                    .padding(horizontal = 6.dp),
                                fontSize = 14.sp
                            )
                        }
                        Text(
                            text = coin.symbol,
                            style = LocalTextStyle.current.copy(lineHeight = 2.sp),
                            fontSize = 60.sp,
                            color = Black
                        )
                        coin.name?.let {
                            Text(
                                text = it,
                                style = TextStyle(fontSize = 30.sp, color = Black)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(192.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(White)
                            .padding(6.dp)
                    ) {
                        val price = coinTicker?.quotes?.usd?.price
                        val formattedPrice = String.format("%.2f", price).replace(",", ".")
                        Text(
                            text = "$ $formattedPrice",
                            style = TextStyle(
                                color = Black,
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
                        SingleLineChart(
                            pointData = pointsData,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(Color(0xff2C2A2A))
                        )
                        Divider()
                        Row {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .background(Red))
                            Box(modifier = Modifier
                                .height(100.dp)
                                .background(Blue))
                        }
                    }
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    coin.tags?.forEach { tag ->
                        CoinTag(tag = tag)
                    }
                }
                CoinDetailTabs(
                )
            }

        }
    }
    if (coinDetailState.error.isNotBlank()) {
        Text(
            text = coinDetailState.error,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
    if (coinDetailState.isLoading) {
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
                    .size(94.dp)
                    .background(Color.Green)
            )
        }
    }


}