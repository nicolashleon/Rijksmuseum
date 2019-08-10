package com.rijksmuseum.sample.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class FormatQueryInterceptor : Interceptor {

    companion object {
        private const val FORMAT_KEY = "format"
        private const val FORMAT_VALUE = "json"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter(FORMAT_KEY, FORMAT_VALUE).build()
        return chain.proceed(chain.request().newBuilder().url(url).build())
    }

}