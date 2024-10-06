package com.example.shared.data.remote.dto

import com.example.shared.domain.model.CoinMarket
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinMarketDto(
    @SerialName("adjusted_volume_24h_share")
    val adjustedVolume24hShare: Double,
    @SerialName("base_currency_id")
    val baseCurrencyId: String,
    @SerialName("base_currency_name")
    val baseCurrencyName: String,
    val category: String,
    @SerialName("exchange_id")
    val exchangeId: String,
    @SerialName("exchange_name")
    val exchangeName: String,
    @SerialName("fee_type")
    val feeType: String,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("market_url")
    val marketUrl: String? = null,
    val outlier: Boolean,
    val pair: String,
    @SerialName("quote_currency_id")
    val quoteCurrencyId: String,
    @SerialName("quote_currency_name")
    val quoteCurrencyName: String,
    val quotes: MarketsQuotes,
    @SerialName("trust_score")
    val trustScore: String
)

fun CoinMarketDto.toCoinMarket(): CoinMarket {
    return CoinMarket(
        adjustedVolume24hShare,
        baseCurrencyId,
        baseCurrencyName,
        category,
        exchangeId,
        exchangeName,
        feeType,
        lastUpdated,
        marketUrl,
        outlier,
        pair,
        quoteCurrencyId,
        quoteCurrencyName,
        quotes,
        trustScore
    )
}