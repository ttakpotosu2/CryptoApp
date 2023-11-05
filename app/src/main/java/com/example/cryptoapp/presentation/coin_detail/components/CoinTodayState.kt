package com.example.cryptoapp.presentation.coin_detail.components

import com.example.cryptoapp.data.remote.dto.TodayDto

data class CoinTodayState(
    val isLoading: Boolean = false,
    val coinToday: List<TodayDto> = emptyList(),
    val error: String = ""
)