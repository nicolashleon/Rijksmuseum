package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.repositories.EventRepository
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import kotlinx.coroutines.launch
import java.util.*

class EventListViewModel(private val eventRepository: EventRepository) : ViewModel() {
    val events = MediatorLiveData<List<DelegateUIModel>>()
    val loader = MutableLiveData<Boolean>()

    fun getNextWeekEvents(startDate: Date) {
        viewModelScope.launch {
            loader.postValue(true)
            try {
                val eventList = eventRepository.getNextWeekEvents(startDate)
                if (eventList.isNotEmpty()) {
                    events.postValue(eventList)
                } else {
                    events.postValue(listOf(EmptyStateItem(R.string.txt_empty_state_event_list)))
                }

            } catch (e: Exception) {
                println(e)
                events.postValue(emptyList())
            } finally {
                loader.postValue(false)
            }
        }
    }

}
