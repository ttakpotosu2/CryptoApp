package com.example.shared.data.remote.dto


import com.example.shared.domain.model.CoinTicker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinTickerDto(
    @SerialName("beta_value")
    val betaValue: Double,
    @SerialName("circulating_supply")
    val circulatingSupply: Long,
    @SerialName("first_data_at")
    val firstDataAt: String,
    val id: String,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("max_supply")
    val maxSupply: Long,
    val name: String,
    val quotes: TickersQuotes,
    val rank: Int,
    val symbol: String,
    @SerialName("total_supply")
    val totalSupply: Long
){
    fun toCoinTicker(): CoinTicker {
        return CoinTicker(
            betaValue, circulatingSupply, firstDataAt, id, lastUpdated, maxSupply, name, quotes, rank, symbol, totalSupply
        )
    }
}