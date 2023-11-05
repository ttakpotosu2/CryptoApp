package com.example.cryptoapp.presentation.coins_list

import com.example.cryptoapp.domain.model.Coins

data class CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<Coins> = emptyList(),
    val error: String = ""
)
