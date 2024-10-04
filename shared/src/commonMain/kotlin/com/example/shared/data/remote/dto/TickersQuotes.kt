package com.example.shared.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickersQuotes(
    @SerialName("USD")
    val usd: TickersUSD
)