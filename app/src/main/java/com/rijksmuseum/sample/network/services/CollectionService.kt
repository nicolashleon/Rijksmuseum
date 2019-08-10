package com.rijksmuseum.sample.network.services

import com.rijksmuseum.sample.models.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CollectionService {

    @GET("api/nl/collection")
    suspend fun getCollection(
        @Query("maker") maker: String,
        @Query("imgonly") imageOnly: Boolean = true
    ): CollectionResponse
}