package com.example.cryptoapp.presentation.coin_tools.components

data class ToolScreenState(
    val fromCode: String = "USD",
    val toCode: String = "BTC",
    val fromValue: String = "0.00",
    val toValue: String = "0.00",
    val selection: SelectionState = SelectionState.FROM,
    val error: String? = null
)

enum class SelectionState {
    FROM, TO
}
