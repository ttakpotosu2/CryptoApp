package com.example.shared.presentation.states

import com.example.shared.domain.model.CoinMarket

data class CoinMarketsState(
	val isLoading: Boolean = false,
	val coinMarkets: List<CoinMarket>? = null,
	val error: String = ""
)