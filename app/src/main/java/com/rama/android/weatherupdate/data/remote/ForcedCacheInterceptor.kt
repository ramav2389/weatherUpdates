package com.rama.android.weatherupdate.data.remote

import com.rama.android.weatherupdate.utils.network.NetworkHelper
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class ForcedCacheInterceptor(val networkHelper: NetworkHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (!networkHelper.isNetworkConnected()) builder.cacheControl(CacheControl.FORCE_CACHE)
        return chain.proceed(builder.build())
    }
}