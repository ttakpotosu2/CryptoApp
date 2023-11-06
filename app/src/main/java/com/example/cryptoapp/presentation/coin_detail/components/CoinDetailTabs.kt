package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinDetailTabs() {

    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Price", "Markets", "About", "Events")
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(tabIndex){
        pagerState.animateScrollToPage(tabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
        if (!pagerState.isScrollInProgress){
            tabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed{ index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {tabIndex = index},
                    text = { Text(text = title) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when(index){
                0 -> { DetailScreenPriceTab() }
                1 -> { DetailScreenMarketsTab() }
                2 -> { DetailScreenAboutTab() }
                3 -> {}
            }
        }
    }
}