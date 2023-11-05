package com.example.cryptoapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TickersQuotes(
    @SerializedName("USD")
    val usd: TickersUSD
)