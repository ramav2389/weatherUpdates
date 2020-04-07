package com.rama.android.weatherupdate.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("main")
    @Expose
    var cityList: List<HomeActivity>,
    @SerializedName("weather")
    @Expose
    var banners: List<Any>,
    @SerializedName("wind")
    @Expose
    var contents: List<HomeContent>
)