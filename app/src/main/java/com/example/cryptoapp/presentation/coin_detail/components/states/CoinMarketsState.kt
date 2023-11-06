package com.example.cryptoapp.presentation.coin_detail.components.states

import com.example.cryptoapp.domain.model.CoinMarket

data class CoinMarketsState(
    val isLoading: Boolean = false,
    val coinMarkets: List<CoinMarket>? = null,
    val error: String = ""
)