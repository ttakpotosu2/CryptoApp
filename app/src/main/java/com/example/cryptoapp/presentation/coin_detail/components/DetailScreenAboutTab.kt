package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.utils.DataUtils
import com.example.cryptoapp.presentation.coin_detail.components.charts.SingleLineChart
import com.example.shared.presentation.viewmodels.CoinDetailViewModel
import com.example.shared.presentation.viewmodels.CoinTickerViewModel
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.theme.Chakrapetch
import com.example.cryptoapp.presentation.ui.theme.Monorama
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalLayoutApi::class, KoinExperimentalAPI::class)
@Composable
fun DetailScreenAboutTab(
    coinTickerViewModel: CoinTickerViewModel = koinViewModel(),
    coinDetailViewModel: CoinDetailViewModel = koinViewModel()
) {
    val coinTickerState = coinTickerViewModel.state.value
    val coinDetailState = coinDetailViewModel.state.value
    val pointsData = remember { DataUtils.getLineChartData(listSize = 13, maxRange = 30) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        val coinTicker = coinTickerState.coinTicker
        val coinDetail = coinDetailState.coin
        SingleLineChart(pointData = pointsData,)

        MarketsInfo(label = "Markets", data = coinTicker?.quotes?.usd?.marketCap?.addCommas().toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "Total Supply", data = coinTicker?.totalSupply?.addCommas().toString())
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(
            label = "In Circulation",
            data = coinTicker?.circulatingSupply?.addCommas().toString()
        )
        Spacer(modifier = Modifier.height(6.dp))
        MarketsInfo(label = "Total volumes", data = coinTicker?.maxSupply?.addCommas().toString())

        if (coinDetail != null) {
            coinDetail.description?.let {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = it,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
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
                fontFamily = Monorama,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        coinDetail?.team?.forEach {
            TeamListItem(teamMember = it)
            Spacer(modifier = Modifier.height(medium))
        }
    }
}