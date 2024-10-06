package com.example.shared.domain.model

import com.example.shared.data.remote.dto.TickersQuotes

data class CoinTicker(
    val betaValue: Double,
    val circulatingSupply: Long? = null,
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
