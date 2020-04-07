package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("message")
    @Expose
    private var message: String,
    @SerializedName("status_code")
    @Expose
    private var statusCode: Int
)