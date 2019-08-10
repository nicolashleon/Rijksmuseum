package com.rijksmuseum.sample.network.services

import com.rijksmuseum.sample.models.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {

    @GET("api/nl/agenda/{year}-{month}-{day}")
    suspend fun getEvents(
        @Path("year") year: Int, @Path("month") month: Int,
        @Path("day") day: Int
    ): EventResponse
}