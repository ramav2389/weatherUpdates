package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Step2Response(
    @SerializedName("meta")
    @Expose
    val meta: Meta,
    @SerializedName("data")
    @Expose
    val data: Step2Data
)