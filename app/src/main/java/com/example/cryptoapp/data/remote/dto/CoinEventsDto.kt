package com.example.cryptoapp.data.remote.dto


import com.example.shared.domain.model.CoinEvents
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinEventsDto (
    val date: String,
    @SerialName("date_to")
    val dateTo: String,
    val description: String,
    val id: String,
    @SerialName("is_conference")
    val isConference: Boolean,
    val link: String,
    val name: String,
    @SerialName("proof_image_link")
    val proofImageLink: String
)

fun CoinEventsDto.toEvents(): CoinEvents {
    return CoinEvents(
        date, dateTo, description, id, isConference, link, name, proofImageLink
    )
}

