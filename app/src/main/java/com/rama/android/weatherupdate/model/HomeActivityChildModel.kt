package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeActivityChildModel(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("content_image_title")
    @Expose
    val title: String,
    @SerializedName("content_image")
    @Expose
    val image: String,
    @SerializedName("content_image_redirect_url")
    @Expose
    val url: String
)