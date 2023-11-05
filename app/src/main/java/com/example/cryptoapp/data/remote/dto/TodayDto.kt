package com.example.cryptoapp.data.remote.dto

import com.example.cryptoapp.domain.model.Today
import com.google.gson.annotations.SerializedName

data class TodayDto(
    val close: Double,
    val high: Double,
    val low: Double,
    @SerializedName("market_cap")
    val marketCap: Long,
    val `open`: Double,
    @SerializedName("time_close")
    val timeClose: String,
    @SerializedName("time_open")
    val timeOpen: String,
    val volume: Long
){
    fun toToday(): Today {
        return Today(
            close, high, low, marketCap, open, timeClose, timeOpen, volume
        )
    }
}