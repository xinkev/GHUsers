package com.xinkev.githubusers.data.remote

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheControlInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder()
            .maxAge(5, TimeUnit.MINUTES)
            .build()
        val requestWithCacheControl = chain.request().newBuilder()
            .cacheControl(cacheControl)
            .build()
        return chain.proceed(requestWithCacheControl)
    }
}
