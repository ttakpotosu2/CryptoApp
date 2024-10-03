package com.example.cryptoapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickersQuotes(
    @SerialName("USD")
    val usd: TickersUSD
)