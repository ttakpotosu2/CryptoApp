package com.example.shared.domain.model

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
