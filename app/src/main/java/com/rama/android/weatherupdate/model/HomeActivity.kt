package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeActivity(
    @SerializedName("temp")
    @Expose
    private val temp: Int,
    @SerializedName("temp_min")
    @Expose
    private val temp_min: String,
    @SerializedName("temp_max")
    @Expose
    private val temp_max: String,
    @SerializedName("pressure")
    @Expose
    private val pressure: String
)