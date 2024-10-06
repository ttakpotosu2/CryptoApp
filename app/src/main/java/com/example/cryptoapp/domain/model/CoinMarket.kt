package com.example.cryptoapp.domain.model

import com.example.shared.data.remote.dto.MarketsQuotes

data class CoinMarket (
	val adjustedVolume24hShare: Double,
	val baseCurrencyId: String,
	val baseCurrencyName: String,
	val category: String,
	val exchangeId: String,
	val exchangeName: String,
	val feeType: String,
	val lastUpdated: String,
	val marketUrl: String? = "",
	val outlier: Boolean,
	val pair: String,
	val quoteCurrencyId: String,
	val quoteCurrencyName: String,
	val quotes: MarketsQuotes,
	val trustScore: String
)
