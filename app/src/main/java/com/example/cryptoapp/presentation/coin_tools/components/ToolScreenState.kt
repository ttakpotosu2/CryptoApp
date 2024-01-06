package com.example.cryptoapp.presentation.coin_tools.components

import java.text.DecimalFormat

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
    val numberFormat: DecimalFormat = DecimalFormat("#0.000000000"),
    val toValue: String = numberFormat.format((amount * price))
)

enum class SelectionState {
    FROM, TO
}