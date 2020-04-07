package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Step2Data(
    @SerializedName("cafe")
    @Expose
    val step2: Step2
)