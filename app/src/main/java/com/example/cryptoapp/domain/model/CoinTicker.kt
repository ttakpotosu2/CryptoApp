package com.example.cryptoapp.domain.model

import com.example.cryptoapp.data.remote.dto.TickersQuotes

data class CoinTicker(
    val betaValue: Double,
    val circulatingSupply: Long,
    val firstDataAt: String,
    val id: String,
    val lastUpdated: String,
    val maxSupply: Long,
    val name: String,
    val quotes: TickersQuotes,
    val rank: Int,
    val symbol: String,
    val totalSupply: Long
)
