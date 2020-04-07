package com.rama.android.weatherupdate.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ResponseCacheInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(chain.request())
        if (request.url().encodedPath() == "/data/2.5/group") {
            return response.newBuilder()
                .removeHeader("Pragma")
                .addHeader("Cache-Control", "public, max-age=86400") // 1 day in seconds
                .build()
        }

        return response
    }
}