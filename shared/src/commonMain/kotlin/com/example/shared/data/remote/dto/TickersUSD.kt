package com.example.shared.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickersUSD(
    @SerialName("ath_date")
    val athDate: String,
    @SerialName("ath_price")
    val athPrice: Double,
    @SerialName("market_cap")
    val marketCap: Long,
    @SerialName("market_cap_change_24h")
    val marketCapChange24h: Double,
    @SerialName("percent_change_12h")
    val percentChange12h: Double,
    @SerialName("percent_change_15m")
    val percentChange15m: Double,
    @SerialName("percent_change_1h")
    val percentChange1h: Double,
    @SerialName("percent_change_1y")
    val percentChange1y: Double,
    @SerialName("percent_change_24h")
    val percentChange24h: Double,
    @SerialName("percent_change_30d")
    val percentChange30d: Double,
    @SerialName("percent_change_30m")
    val percentChange30m: Double,
    @SerialName("percent_change_6h")
    val percentChange6h: Double,
    @SerialName("percent_change_7d")
    val percentChange7d: Double,
    @SerialName("percent_from_price_ath")
    val percentFromPriceAth: Double,
    val price: Double,
    @SerialName("volume_24h")
    val volume24h: Double,
    @SerialName("volume_24h_change_24h")
    val volume24hChange24h: Double
)