package com.example.shared.domain.model

data class CoinConverter(
    val amount: Double,
    val baseCurrencyId: String,
    val baseCurrencyName: String,
    val basePriceLastUpdated: String,
    val price: Double,
    val quoteCurrencyId: String,
    val quoteCurrencyName: String,
    val quotePriceLastUpdated: String
)
