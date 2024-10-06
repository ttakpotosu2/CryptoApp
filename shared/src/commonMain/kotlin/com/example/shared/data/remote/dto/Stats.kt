package com.example.shared.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val contributors: Int? = null,
    val followers: Int? = null,
    val stars: Int? = null,
    val subscribers: Int? = null
)