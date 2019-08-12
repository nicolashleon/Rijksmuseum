package com.rijksmuseum.sample.repositories

import com.rijksmuseum.sample.network.services.EventService
import com.rijksmuseum.sample.ui.models.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.util.*

class EventRepository(private val eventService: EventService) {

    suspend fun getEvents(date: Date) = withContext(Dispatchers.IO) {
        //TODO map the result to UI models and return it
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        val eventReposeDeferred = async { eventService.getEvents(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)) }

        eventReposeDeferred.await().options.map {
            Event(it.exposition.name, it.exposition.description, it.exposition.price.amount,
                    it.period.remaining, it.groupType.friendlyName, it.period.startDate,
                    it.period.endDate)
        }
    }
}