package com.example.cryptoapp.presentation.coin_tools.components

data class ToolScreenState(
    val fromCode: String = "USD",
    val fromName: String = "United States Dollars",
    val fromID: String = "usd-us-dollars",
    val fromValue: String = "1.00",
    val toCode: String = "BTC",
    val toName: String = "Bitcoin",
    val toId: String = "btc-bitcoin",
    val toValue: String = "1.00",
    val amount: Int = 1, //TODO: Do we need this or a map? or is this the same as the fromValue?
    val error: String? = null,
    val selection: SelectionState = SelectionState.FROM
)

enum class SelectionState {
    FROM, TO
}