package com.example.teetech

import com.google.gson.annotations.SerializedName

data class TShirt(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("size") val size: String
)