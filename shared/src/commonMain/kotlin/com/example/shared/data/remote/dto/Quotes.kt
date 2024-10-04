package com.example.shared.data.remote.dto


import kotlinx.serialization.SerialName

data class Quotes(
    @SerialName("USD")
    val uSD: USD
)