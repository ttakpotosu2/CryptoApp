package com.example.shared.data.remote.dto

import com.example.shared.domain.model.Today
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodayDto(
    val close: Double,
    val high: Double,
    val low: Double,
    @SerialName("market_cap")
    val marketCap: Long,
    val `open`: Double,
    @SerialName("time_close")
    val timeClose: String,
    @SerialName("time_open")
    val timeOpen: String,
    val volume: Long
){
    fun toToday(): Today {
        return Today(
            close, high, low, marketCap, open, timeClose, timeOpen, volume
        )
    }
}