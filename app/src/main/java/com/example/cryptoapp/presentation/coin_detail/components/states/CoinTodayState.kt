package com.example.cryptoapp.presentation.coin_detail.components.states

import com.example.cryptoapp.domain.model.Today

data class CoinTodayState(
    val isLoading: Boolean = false,
    val coinToday: List<Today> = emptyList(),
    val error: String = ""
)