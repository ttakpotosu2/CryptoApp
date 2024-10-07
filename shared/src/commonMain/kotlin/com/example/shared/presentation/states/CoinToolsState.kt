package com.example.shared.presentation.states

import com.example.shared.domain.model.CoinConverter

data class CoinToolsState(
	val isLoading: Boolean = false,
	val coinTools: CoinConverter? = null,
	val error: String = ""
)