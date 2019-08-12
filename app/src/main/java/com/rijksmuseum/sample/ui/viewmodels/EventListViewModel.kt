package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.repositories.EventRepository
import com.rijksmuseum.sample.ui.models.Event
import kotlinx.coroutines.launch
import java.util.*

class EventListViewModel(private val eventRepository: EventRepository) : ViewModel() {
    val events = MutableLiveData<List<Event>>()
    val loader = MutableLiveData<Boolean>()

    fun getEvents(startDate: Date) {
        viewModelScope.launch {
            loader.postValue(true)
            try {
                events.postValue(eventRepository.getEvents(startDate))
            } catch (e: Exception) {
                println(e)
            } finally {
                loader.postValue(false)
            }
        }
    }
}
