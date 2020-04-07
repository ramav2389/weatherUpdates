package com.rama.android.weatherupdate.model

import com.google.gson.annotations.SerializedName

data class Sys(

    @SerializedName("country") val country: String,
    @SerializedName("timezone") val timezone: Double,
    @SerializedName("sunrise") val sunrise: Double,
    @SerializedName("sunset") val sunset: Double
)