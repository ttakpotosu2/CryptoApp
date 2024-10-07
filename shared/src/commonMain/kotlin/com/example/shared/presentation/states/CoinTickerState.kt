package com.example.shared.presentation.states

import com.example.shared.domain.model.CoinTicker

data class CoinTickerState(
	val isLoading: Boolean = false,
	val coinTicker: CoinTicker? = null,
	val error: String = ""
)
