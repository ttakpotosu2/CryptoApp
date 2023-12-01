package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.common.utils.DataUtils
import coil.compose.SubcomposeAsyncImage
import com.example.cryptoapp.presentation.coin_detail.components.charts.SingleLineChart
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinDetailViewModel
import com.example.cryptoapp.presentation.coin_detail.viewmodels.CoinTickerViewModel
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.theme.Chakrapetch
import com.example.cryptoapp.presentation.ui.theme.Monorama

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreenAboutTab(
    coinTickerViewModel: CoinTickerViewModel = hiltViewModel(),
    coinDetailViewModel: CoinDetailViewModel = hiltViewModel()
) {
    val coinTickerState = coinTickerViewModel.state.value
    val coinDetailState = coinDetailViewModel.state.value
    val pointsData = DataUtils.getLineChartData(listSize = 13, maxRange = 30)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {

        val coinDetail = coinDetailState.coin
        SingleLineChart(
            pointData = pointsData,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        val coinTicker = coinTickerState.coinTicker

        MarketsInfo(label = "Markets", data = coinTicker?.quotes?.usd?.marketCap.toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "Total Supply", data = coinTicker?.totalSupply.toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(
            label = "In Circulation",
            data = coinTicker?.circulatingSupply.toString()
        )
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "Total volumes", data = coinTicker?.maxSupply.toString())

        if (coinDetail != null) {
            coinDetail.description?.let {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = it,
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = Chakrapetch,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    ),

                )
            }
        }
        Spacer(modifier = Modifier.height(medium))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (coinDetail != null) {
                coinDetail.tags?.forEach { tag ->
                    CoinTag(tag = tag)
                }
            }
        }
        Spacer(modifier = Modifier.height(medium))
        Text(
            text = "Team Members",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Monorama
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        coinDetail?.team?.forEach {
            TeamListItem(teamMember = it)
            Spacer(modifier = Modifier.height(medium))
        }
    }
}