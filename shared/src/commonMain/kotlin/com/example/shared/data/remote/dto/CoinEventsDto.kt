package com.example.shared.data.remote.dto

import com.example.shared.domain.model.CoinEvents
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinEventsDto (
    val date: String,
    @SerialName("date_to")
    val dateTo: String? = null,
    val description: String,
    val id: String,
    @SerialName("is_conference")
    val isConference: Boolean,
    val link: String,
    val name: String,
    @SerialName("proof_image_link")
    val proofImageLink: String? = null
)

fun CoinEventsDto.toEvents(): CoinEvents {
    return CoinEvents(
        date, dateTo, description, id, isConference, link, name, proofImageLink
    )
}