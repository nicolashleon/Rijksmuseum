package com.rijksmuseum.sample.repositories

import com.rijksmuseum.sample.network.services.EventService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class EventRepository(private val eventService: EventService) {

    suspend fun getEvents(date: Date) = withContext(Dispatchers.IO) {
        //TODO map the result to UI models and return it
        eventService.getEvents(2019, 8, 10)
    }
}