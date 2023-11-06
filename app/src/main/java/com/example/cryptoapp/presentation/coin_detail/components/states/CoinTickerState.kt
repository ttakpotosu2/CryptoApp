package com.example.cryptoapp.presentation.coin_detail.components.states

import com.example.cryptoapp.domain.model.CoinTicker

data class CoinTickerState(
    val isLoading: Boolean = false,
    val coinTicker: CoinTicker? = null,
    val error: String = ""
)
