package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Step2(

    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("banner")
    @Expose
    var banner: String,
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("images")
    @Expose
    var images: List<String>,
    @SerializedName("description")
    @Expose
    var description: String,
    @SerializedName("created_at")
    @Expose
    var createdAt: String,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String
)