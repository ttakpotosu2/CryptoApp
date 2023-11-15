package com.example.cryptoapp.presentation.coin_detail.components.states

import com.example.cryptoapp.domain.model.CoinEvents

data class CoinEventsState(
    val isLoading: Boolean = false,
    val coinEvents: List<CoinEvents>? = null,
    val error: String = ""
)