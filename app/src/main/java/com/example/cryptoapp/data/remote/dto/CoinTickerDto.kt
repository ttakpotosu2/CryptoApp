package com.example.cryptoapp.data.remote.dto


import com.example.cryptoapp.domain.model.CoinTicker
import com.google.gson.annotations.SerializedName

data class CoinTickerDto(
    @SerializedName("beta_value")
    val betaValue: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Int,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    val id: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("max_supply")
    val maxSupply: Int,
    val name: String,
    val quotes: TickersQuotes,
    val rank: Int,
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: Int
){
    fun toCoinTicker(): CoinTicker{
        return CoinTicker(
            betaValue, circulatingSupply, firstDataAt, id, lastUpdated, maxSupply, name, quotes, rank, symbol, totalSupply
        )
    }
}