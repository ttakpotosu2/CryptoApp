package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.presentation.ui.theme.Monorama


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinDetailTabs() {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("About", "Markets", "Events")
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(tabIndex){
        pagerState.animateScrollToPage(tabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
        if (!pagerState.isScrollInProgress){
            tabIndex = pagerState.currentPage
        }
    }
    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            divider = {}
        ) {
            tabs.forEachIndexed{ index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {tabIndex = index},
                    text = {
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 28.sp,
                                color = if (tabIndex == index) { Color.Black } else Color.Black.copy(0.3f),
                                fontFamily = Monorama
                            )
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            when(index){
                0 -> { DetailScreenAboutTab() }
                1 -> { DetailScreenMarketsTab() }
                2 -> { DetailScreenEventsTab() }
            }
        }
    }
}