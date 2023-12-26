package com.example.cryptoapp.data.remote.dto

import com.example.cryptoapp.domain.model.CoinConverter
import com.google.gson.annotations.SerializedName

data class CoinConverterDto(
    val amount: Int,
    @SerializedName("base_currency_id")
    val baseCurrencyId: String,
    @SerializedName("base_currency_name")
    val baseCurrencyName: String,
    @SerializedName("base_price_last_updated")
    val basePriceLastUpdated: String,
    val price: Double,
    @SerializedName("quote_currency_id")
    val quoteCurrencyId: String,
    @SerializedName("quote_currency_name")
    val quoteCurrencyName: String,
    @SerializedName("quote_price_last_updated")
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


