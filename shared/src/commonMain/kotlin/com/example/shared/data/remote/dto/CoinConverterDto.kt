package com.example.shared.data.remote.dto

import com.example.shared.domain.model.CoinConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinConverterDto(
    val amount: Double,
    @SerialName("base_currency_id")
    val baseCurrencyId: String,
    @SerialName("base_currency_name")
    val baseCurrencyName: String,
    @SerialName("base_price_last_updated")
    val basePriceLastUpdated: String,
    val price: Double,
    @SerialName("quote_currency_id")
    val quoteCurrencyId: String,
    @SerialName("quote_currency_name")
    val quoteCurrencyName: String,
    @SerialName("quote_price_last_updated")
    val quotePriceLastUpdated: String
)

fun CoinConverterDto.toCoinConverter(): CoinConverter {
    return CoinConverter(
        amount,
        baseCurrencyId,
        baseCurrencyName,
        basePriceLastUpdated,
        price,
        quoteCurrencyId,
        quoteCurrencyName,
        quotePriceLastUpdated
    )
}


