package com.rama.android.weatherupdate.repository

import com.rama.android.weatherupdate.data.remote.Network
import com.rama.android.weatherupdate.data.remote.NetworkService
import com.rama.android.weatherupdate.model.*
import io.reactivex.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun fetchHomePage(selectedCity: String): Single<List<Data>>? =
        networkService.doHomePageCall(
            selectedCity,
            "metrics",
            Network.AUTHORIZATION_TOKEN,
            Network.ACCEPT,
            Network.CONTENT_TYPE
        ).map {
            it
        }


    fun fetchCafePageList(): Single<Step2Data> =
        networkService.doStep2PageCall().map {
            it.response.data
        }
}