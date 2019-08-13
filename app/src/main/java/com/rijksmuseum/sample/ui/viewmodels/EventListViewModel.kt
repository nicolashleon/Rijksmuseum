package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.repositories.EventRepository
import com.rijksmuseum.sample.ui.Result
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import kotlinx.coroutines.launch
import java.util.*

class EventListViewModel(private val eventRepository: EventRepository) : ViewModel() {

    val events = MutableLiveData<Result<List<DelegateUIModel>>>()

    fun getNextWeekEvents(startDate: Date) {
        viewModelScope.launch {
            events.postValue(Result.Loading())
            try {
                var eventList : List<DelegateUIModel> = eventRepository.getNextWeekEvents(startDate)
                if(eventList.isEmpty()) {
                    eventList = listOf(EmptyStateItem(R.string.txt_empty_state_event_list))
                }
                events.postValue(Result.Success(eventList))

            } catch (e: Exception) {
                println(e)
                events.postValue(Result.Error(throwable = e))
            }
        }
    }

}
