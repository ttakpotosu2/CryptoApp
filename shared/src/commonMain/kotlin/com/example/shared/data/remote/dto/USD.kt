package com.example.shared.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class USD(
    val price: Double,
    @SerialName("volume_24h")
    val volume24h: Double
)