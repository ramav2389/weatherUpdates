package com.rama.android.weatherupdate.data.remote.response

import com.rama.android.weatherupdate.model.Step2Response
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Step2PageResponse(

    @SerializedName("response")
    @Expose
    var response: Step2Response

)