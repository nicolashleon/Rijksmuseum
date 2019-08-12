package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.repositories.EventRepository
import com.rijksmuseum.sample.ui.Result
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import kotlinx.coroutines.launch
import java.util.*

class EventListViewModel(private val eventRepository: EventRepository) : ViewModel() {

    val events = MutableLiveData<Result<List<DelegateUIModel>>>()
    val loader = MutableLiveData<Boolean>()

    fun getNextWeekEvents(startDate: Date) {
        viewModelScope.launch {
            loader.postValue(true)
            try {
                val eventList = eventRepository.getNextWeekEvents(startDate)
                events.postValue(Result(eventList))

            } catch (e: Exception) {
                println(e)
                events.postValue(Result(e))
            } finally {
                loader.postValue(false)
            }
        }
    }

}
