package com.example.shared.presentation.states

import com.example.shared.domain.model.Today

data class CoinTodayState(
	val isLoading: Boolean = false,
	val coinToday: List<Today> = emptyList(),
	val error: String = ""
)