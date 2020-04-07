package com.rama.android.weatherupdate.data.remote

import com.rama.android.weatherupdate.data.remote.response.*
import com.rama.android.weatherupdate.model.Data
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface NetworkService {

    @GET(Endpoints.STEP1)
    fun doHomePageCall(
        @Query("id") selectedCities: String,
        @Query("units") units: String,
        @Query(Network.HEADER_AUTHORIZATION_TOKEN) authorizationToken: String,
        @Header(Network.HEADER_ACCEPT) accept: String,
        @Header(Network.HEADER_CONTENT_TYPE) contentType: String
    ): Single<List<Data>>


    @GET(Endpoints.STEP2)
    fun doStep2PageCall(
        @Header(Network.HEADER_ACCEPT) accept: String = Network.ACCEPT,
        @Header(Network.HEADER_CONTENT_TYPE) contentType: String = Network.CONTENT_TYPE,
        @Header(Network.HEADER_AUTHORIZATION_TOKEN) authorizationToken: String = Network.AUTHORIZATION_TOKEN
    ): Single<Step2PageResponse>

}