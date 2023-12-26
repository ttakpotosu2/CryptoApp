package com.example.cryptoapp.presentation.coin_tools.components

import com.example.cryptoapp.domain.model.CoinConverter

data class CoinToolsState(
    val isLoading: Boolean = false,
    val coinTools: CoinConverter? = null,
    val error: String = ""
)