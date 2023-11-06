package com.example.cryptoapp.data.remote.dto


import com.example.cryptoapp.domain.model.CoinMarket
import com.google.gson.annotations.SerializedName

data class CoinMarketDto(
    @SerializedName("adjusted_volume_24h_share")
    val adjustedVolume24hShare: Double,
    @SerializedName("base_currency_id")
    val baseCurrencyId: String,
    @SerializedName("base_currency_name")
    val baseCurrencyName: String,
    val category: String,
    @SerializedName("exchange_id")
    val exchangeId: String,
    @SerializedName("exchange_name")
    val exchangeName: String,
    @SerializedName("fee_type")
    val feeType: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("market_url")
    val marketUrl: String,
    val outlier: Boolean,
    val pair: String,
    @SerializedName("quote_currency_id")
    val quoteCurrencyId: String,
    @SerializedName("quote_currency_name")
    val quoteCurrencyName: String,
    val quotes: MarketsQuotes,
    @SerializedName("trust_score")
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