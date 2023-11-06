package com.example.cryptoapp.presentation.coin_detail.components.states

import com.example.cryptoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
