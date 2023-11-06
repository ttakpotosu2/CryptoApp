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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.cryptoapp.presentation.coin_detail.components.CoinDetailTabs
import com.example.cryptoapp.presentation.coin_detail.components.CoinTag
import com.example.cryptoapp.presentation.coin_detail.components.DetailScreenPriceTab
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinDetailViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    val coinDetailState = coinDetailViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
    ) {
        coinDetailState.coin?.let { coin ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = coin.symbol, style = TextStyle(fontSize = 60.sp, color = White)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = coin.logo,
                        loading = {
                            CircularProgressIndicator(color = White)
                        },
                        contentDescription = "coin logo",
                        modifier = Modifier.size(20.dp),
                        alignment = Center,
                        contentScale = ContentScale.Crop
                    )
                    coin.name?.let {
                        Text(
                            text = it,
                            style = TextStyle(fontSize = 30.sp, color = White)
                        )
                    }
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
                Spacer(modifier = Modifier.height(15.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    coin.tags?.forEach { tag ->
                        CoinTag(tag = tag)
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                CoinDetailTabs()
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Sell")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Buy")
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
            CircularProgressIndicator(
                modifier = Modifier.align(Center),
                color = White
            )
        }
    }
}