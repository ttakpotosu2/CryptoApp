package com.example.cryptoapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MarketsQuotes(
    @SerializedName("USD")
    val uSD: USD
)