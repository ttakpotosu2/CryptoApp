package com.example.cryptoapp.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.yml.charts.common.extensions.isNotNull
import coil.compose.SubcomposeAsyncImage
import com.example.cryptoapp.presentation.bottomNavBar.CryptoAppBottomNavBar
import com.example.cryptoapp.presentation.coin_detail.components.CoinDetailTabs
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinDetailViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinTickerViewModel
import com.example.cryptoapp.presentation.ui.big
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.theme.Chakrapetch
import com.example.cryptoapp.presentation.ui.theme.Monorama
import com.valentinilk.shimmer.shimmer

@Composable
fun CoinDetailScreen(
    coinTickerViewModel: CoinTickerViewModel = hiltViewModel(),
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val coinTickerState = coinTickerViewModel.state.value
    val coinDetailState = coinDetailViewModel.state.value


    if (coinDetailState.coin.isNotNull()) {
        Scaffold(
            bottomBar = {
                Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
                CryptoAppBottomNavBar(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(horizontal = big, vertical = small),
                    navController = navController
                )
            }
        ) { paddingValues ->
            val coinTicker = coinTickerState.coinTicker

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    // .background(Color(0xFF1C1B1F))
                    .padding(paddingValues)
                    .padding(small)
            ) {
                coinDetailState.coin?.let { coin ->
                    SubcomposeAsyncImage(
                        model = coin.logo,
                        loading = { CircularProgressIndicator(color = White) },
                        contentDescription = "coin logo",
                        modifier = Modifier.size(75.dp),
                        alignment = Center,
                        contentScale = ContentScale.Crop
                    )
                    Row(modifier = Modifier) {
                        Text(
                            text = coin.symbol,
                            style = TextStyle(
                                fontSize = 80.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontFamily = Monorama
                            )
                        )
                        coin.name?.let { text ->
                            Text(
                                text = "/$text",
                                style = TextStyle(
                                    fontSize = 36.sp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontFamily = Monorama
                                ),
                                modifier = Modifier.offset(y = 10.dp)
                            )
                        }
                    }
                    Text(
                        text = "#" + coin.rank.toString(),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontFamily = Chakrapetch
                        ),
                        modifier = Modifier
                            .clip(RectangleShape)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 4.dp),
                    )
                    Text(
                        text = if (coin.isActive == true) "active" else "inactive",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontFamily = Chakrapetch
                        )

                    )
                    Row {
                        val price = coinTicker?.quotes?.usd?.price
                        val formattedPrice = String.format("%.2f", price).replace(",", ".")
                        Text(
                            text = "$ $formattedPrice",
                            style = TextStyle(
                                fontFamily = Monorama,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        val figure = coinTicker?.quotes?.usd?.volume24hChange24h
                        if (figure != null) {
                            Text(
                                text = "${figure}%",
                                style = TextStyle(
                                    color = if (figure < 0) Red else Green,
                                    fontSize = 18.sp,
                                    fontFamily = Monorama
                                ),
                                modifier = Modifier.offset(y = 7.dp)
                            )
                        }
                    }
                    Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
                    // Tabs
                    CoinDetailTabs()
                }
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
                .shimmer(),
            contentAlignment = Center
        ) {
            Box(
                modifier = Modifier
                    .size(94.dp)
                    .background(Green)
            )
        }
    }
}