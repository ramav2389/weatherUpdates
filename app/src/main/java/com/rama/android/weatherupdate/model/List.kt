package com.rama.android.weatherupdate.model

import com.google.gson.annotations.SerializedName

data class ListParams(

    @SerializedName("coord") val coord: Coord,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Double,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)