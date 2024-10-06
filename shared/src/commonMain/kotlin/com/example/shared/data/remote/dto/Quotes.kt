package com.example.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quotes(
    @SerialName("USD")
    val uSD: USD
)