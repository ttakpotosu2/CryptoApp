package com.example.cryptoapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class USD(
    val price: Double,
    @SerializedName("volume_24h")
    val volume24h: Double
)