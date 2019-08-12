package com.rijksmuseum.sample

import android.app.Application
import com.rijksmuseum.sample.network.interceptors.APIAuthInterceptor
import com.rijksmuseum.sample.network.interceptors.CacheInterceptor
import com.rijksmuseum.sample.network.interceptors.FormatQueryInterceptor
import com.rijksmuseum.sample.network.services.CollectionService
import com.rijksmuseum.sample.network.services.EventService
import com.rijksmuseum.sample.repositories.CollectionRepository
import com.rijksmuseum.sample.repositories.EventRepository
import com.rijksmuseum.sample.ui.viewmodels.CollectionViewModel
import com.rijksmuseum.sample.ui.viewmodels.EventListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.*


class RijksmuseumApplication : Application() {

    private val appModule = module {

        single {

            //setup cache
            val httpCacheDirectory = File(this@RijksmuseumApplication.cacheDir, "responses")
            val cacheSize = 10 * 1024 * 1024L // 10MB
            val cache = Cache(httpCacheDirectory, cacheSize)
            val builder = OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(FormatQueryInterceptor())
                    .addInterceptor(APIAuthInterceptor())
                    .addNetworkInterceptor(CacheInterceptor(this@RijksmuseumApplication))
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            }
            builder.build()
        }
        single {
            Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter())
                    .build()
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
        single {
            EventRepository(get())
        }
        single {
            CollectionRepository(get())
        }

        viewModel { EventListViewModel(get()) }

        viewModel { CollectionViewModel(get()) }
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