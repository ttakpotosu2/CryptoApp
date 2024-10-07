package com.example.shared.presentation.states

import com.example.shared.domain.model.Coins

data class CoinsListState(
	val isLoading: Boolean = false,
	val coins: List<Coins> = emptyList(),
	val error: String = ""
)
