package com.example.cryptoapp.domain.model

data class Today(
    val close: Double,
    val high: Double,
    val low: Double,
    val marketCap: Long,
    val `open`: Double,
    val timeClose: String,
    val timeOpen: String,
    val volume: Long
)
