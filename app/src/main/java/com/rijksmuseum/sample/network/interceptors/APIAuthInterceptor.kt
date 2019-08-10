package com.rijksmuseum.sample.network.interceptors

import com.rijksmuseum.sample.BuildConfig
import okhttp3.*

class APIAuthInterceptor : Interceptor {

    companion object {
        private const val KEY = "key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter(KEY, BuildConfig.API_KEY).build()
        return chain.proceed(chain.request().newBuilder().url(url).build())
    }

}