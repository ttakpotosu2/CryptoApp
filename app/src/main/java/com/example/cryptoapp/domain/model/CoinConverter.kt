package com.example.cryptoapp.domain.model

data class CoinConverter(
    val amount: Int,
    val baseCurrencyId: String,
    val baseCurrencyName: String,
    val basePriceLastUpdated: String,
    val price: Double,
    val quoteCurrencyId: String,
    val quoteCurrencyName: String,
    val quotePriceLastUpdated: String
)
