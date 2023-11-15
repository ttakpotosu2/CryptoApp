package com.example.cryptoapp.data.remote.dto

import com.example.cryptoapp.domain.model.Coins
import com.google.gson.annotations.SerializedName

data class CoinsDto (
    val id: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("is_new") val isNew: Boolean,
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