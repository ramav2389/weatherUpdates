package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *Created by ramavijayapandian on 4/7/20.
 **/
data class CityData(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("country")
    @Expose
    var contents: String?
)