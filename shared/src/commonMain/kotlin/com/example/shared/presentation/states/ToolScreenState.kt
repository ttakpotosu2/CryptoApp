package com.example.shared.presentation.states

data class ToolScreenState(
	val fromCode: String = "USD",
	val fromName: String = "United States Dollars",
	val fromID: String = "usd-us-dollars",
	val fromValue: String = "1.0",
	val toCode: String = "BTC",
	val toName: String = "Bitcoin",
	val toId: String = "btc-bitcoin",
	val amount: Double = 1.0,
	val price: Double = 1.0,
	val error: String? = null,
	val selection: SelectionState = SelectionState.FROM,
	//val numberFormat: DecimalFormat = DecimalFormat("#0,000.000"),
	val toValue: String = (amount * price).toString()
)

enum class SelectionState {
    FROM, TO
}