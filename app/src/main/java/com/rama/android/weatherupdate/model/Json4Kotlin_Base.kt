package com.rama.android.weatherupdate.model


import com.google.gson.annotations.SerializedName


data class Json4Kotlin_Base(
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<ListParams>
)