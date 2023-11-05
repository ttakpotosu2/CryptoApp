package com.example.cryptoapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("USD")
    val uSD: USD
)