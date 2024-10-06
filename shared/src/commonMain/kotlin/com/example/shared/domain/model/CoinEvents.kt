package com.example.shared.domain.model

data class CoinEvents (
    val date: String,
    val dateTo: String? = "not available",
    val description: String,
    val id: String,
    val isConference: Boolean,
    val link: String,
    val name: String,
    val proofImageLink: String? = "not available"
)