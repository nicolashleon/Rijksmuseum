package com.rijksmuseum.sample

import android.app.Application
import com.rijksmuseum.sample.network.interceptors.APIAuthInterceptor
import com.rijksmuseum.sample.network.interceptors.FormatQueryInterceptor
import com.rijksmuseum.sample.network.services.CollectionService
import com.rijksmuseum.sample.network.services.EventService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RijksmuseumApplication : Application() {

    private val appModule = module {

        single {
            val builder = OkHttpClient.Builder()
                .addInterceptor(FormatQueryInterceptor())
                .addInterceptor(APIAuthInterceptor())
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            }
            builder.build()
        }
        single {
            Moshi.Builder().build()
        }
        single {
            Retrofit.Builder()
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .baseUrl(BuildConfig.API_URL)
                .build()
        }
        single {
            val retrofit: Retrofit = get()
            retrofit.create(EventService::class.java)
        }

        single {
            val retrofit: Retrofit = get()
            retrofit.create(CollectionService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RijksmuseumApplication)
            modules(appModule)
        }
    }
}