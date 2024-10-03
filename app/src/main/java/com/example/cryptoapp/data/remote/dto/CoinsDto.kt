package com.example.cryptoapp.data.remote.dto

import com.example.shared.domain.model.Coins
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsDto (
    val id: String,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("is_new") val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)
fun CoinsDto.toCoins(): Coins {
    return Coins(
        id,
        isActive,
        name,
        rank,
        symbol
    )
}