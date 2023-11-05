package com.example.cryptoapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CoinEvents(
    val date: String,
    @SerializedName("date_to")
    val dateTo: Any,
    val description: String,
    val id: String,
    @SerializedName("is_conference")
    val isConference: Boolean,
    val link: String,
    val name: String,
    @SerializedName("proof_image_link")
    val proofImageLink: Any
)