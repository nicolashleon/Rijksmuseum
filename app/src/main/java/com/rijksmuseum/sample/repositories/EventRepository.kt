package com.rijksmuseum.sample.repositories

import com.rijksmuseum.sample.models.EventResponse
import com.rijksmuseum.sample.network.services.EventService
import com.rijksmuseum.sample.ui.models.Event
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class EventRepository(private val eventService: EventService) {

    suspend fun getNextWeekEvents(date: Date) = withContext(Dispatchers.IO) {
        val calendar = Calendar.getInstance().apply {
            time = date
        }

        val serviceCalls = ArrayList<Deferred<EventResponse>>()

        for (i in 0 until 8) {
            serviceCalls.add(async {
                eventService.getEvents(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH) + i)
            })
        }

        serviceCalls.awaitAll().flatMap { eventResponse -> eventResponse.options }.map {
            Event(it.exposition.name, it.exposition.description, it.exposition.price.amount,
                    it.period.remaining, it.groupType.friendlyName, it.period.startDate,
                    it.period.endDate)
        }
    }
}