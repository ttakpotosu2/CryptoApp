package com.example.shared.presentation.coin_tools.components

import com.example.shared.domain.model.CoinConverter

data class CoinToolsState(
	val isLoading: Boolean = false,
	val coinTools: CoinConverter? = null,
	val error: String = ""
)