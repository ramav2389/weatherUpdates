package com.rama.android.weatherupdate.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.rama.android.weatherupdate.BuildConfig
import com.rama.android.weatherupdate.utils.network.NetworkHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Network {

    const val HEADER_ACCEPT = "Accept"
    const val HEADER_CONTENT_TYPE = "Content-Type"
    const val HEADER_AUTHORIZATION_TOKEN = "APPID"

    private const val NETWORK_CALL_TIMEOUT = 60
    internal lateinit var AUTHORIZATION_TOKEN: String
    internal lateinit var CONTENT_TYPE: String
    internal lateinit var ACCEPT: String

    fun create(
        networkHelper: NetworkHelper,
        baseUrl: String,
        authorizationToken: String,
        contentType: String,
        accept: String,
        cacheDir: File,
        cacheSize: Long
    ): NetworkService {
        AUTHORIZATION_TOKEN = authorizationToken
        CONTENT_TYPE = contentType
        ACCEPT = accept

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(cacheDir, cacheSize))
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE
                        })
                    .addNetworkInterceptor(ResponseCacheInterceptor())
                    .addInterceptor(ForcedCacheInterceptor(networkHelper))
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

}