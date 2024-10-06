package com.example.shared.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LinksExtended(
    val stats: Stats? = null,
    val type: String,
    val url: String
)