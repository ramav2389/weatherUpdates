package com.rama.android.weatherupdate.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rama.android.weatherupdate.model.Data
import com.rama.android.weatherupdate.model.HomeActivity
import com.rama.android.weatherupdate.model.Response

data class HomePageResponse(

    @SerializedName("list")
    @Expose
    var response: List<Data>

)