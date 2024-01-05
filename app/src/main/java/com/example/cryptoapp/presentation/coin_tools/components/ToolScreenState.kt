package com.example.cryptoapp.presentation.coin_tools.components

data class ToolScreenState(
    val fromCode: String = "USD",
    val fromName: String = "United States Dollars",
    val fromID: String = "usd-us-dollars",
    val fromValue: String = "1",
    val toCode: String = "BTC",
    val toName: String = "Bitcoin",
    val toId: String = "btc-bitcoin",
    val amount: Double = 1.0, //TODO: Do we need this or a map? or is this the same as the fromValue?
    val price: Double = 0.0000227352,
    val error: String? = null,
    val selection: SelectionState = SelectionState.FROM,
    val toValue: String = (amount * price).toString()
)

enum class SelectionState {
    FROM, TO
}