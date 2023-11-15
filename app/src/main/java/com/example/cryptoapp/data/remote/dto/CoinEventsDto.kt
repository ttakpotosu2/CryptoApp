package com.example.cryptoapp.data.remote.dto


import com.example.cryptoapp.domain.model.CoinEvents
import com.google.gson.annotations.SerializedName

data class CoinEventsDto (
    val date: String,
    @SerializedName("date_to")
    val dateTo: String,
    val description: String,
    val id: String,
    @SerializedName("is_conference")
    val isConference: Boolean,
    val link: String,
    val name: String,
    @SerializedName("proof_image_link")
    val proofImageLink: String
)

fun CoinEventsDto.toEvents(): CoinEvents {
    return CoinEvents(
        date, dateTo, description, id, isConference, link, name, proofImageLink
    )
}

