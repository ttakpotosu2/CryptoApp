package com.example.shared.presentation.coin_detail.components.states

import com.example.shared.domain.model.CoinEvents

data class CoinEventsState(
	val isLoading: Boolean = false,
	val coinEvents: List<CoinEvents>? = null,
	val error: String = ""
)