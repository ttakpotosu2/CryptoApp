package com.example.cryptoapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cryptoapp.presentation.ui.OnBoardingButton
import com.example.cryptoapp.presentation.ui.OnboardingNextButton
import com.example.cryptoapp.presentation.ui.PageIndicator
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.IndicatorWidth
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    toMainScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { pages.size }
        )

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1, 2 -> listOf("Back", "Next")
                    3 -> listOf("Back", "Let's Get Started")
                    else -> emptyList()
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(small)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(IndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    OnboardingNextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                OnBoardingButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 3) {
                                toMainScreen()
                            } else {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
    }
}