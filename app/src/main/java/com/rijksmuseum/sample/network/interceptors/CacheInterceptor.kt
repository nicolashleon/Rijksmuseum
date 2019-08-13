package com.rijksmuseum.sample.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response


class CacheInterceptor(private val context: Context) : Interceptor {

    companion object {
        private const val PRAGMA_HEADER = "Pragma"
        private const val CACHE_CONTROL_HEADER = "Cache-Control"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        return if (isNetworkAvailable()) {
            val maxAge = 60 * 5

            originalResponse.newBuilder()
                    .removeHeader(PRAGMA_HEADER)
                    .header(CACHE_CONTROL_HEADER, "public, max-age=$maxAge")
                    .build()
        } else {
            val maxStale = 60 * 5
            originalResponse.newBuilder()
                    .removeHeader(PRAGMA_HEADER)
                    .header(CACHE_CONTROL_HEADER, "public, only-if-cached, max-stale=$maxStale")
                    .build()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager?
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}