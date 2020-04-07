package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("meta")
    @Expose
    var meta: Meta,
    @SerializedName("list")
    @Expose
    var data: Data
)