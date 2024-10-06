package com.example.cryptoapp.presentation.coin_detail.components.states

import com.example.shared.domain.model.Today

data class CoinTodayState(
	val isLoading: Boolean = false,
	val coinToday: List<Today> = emptyList(),
	val error: String = ""
)