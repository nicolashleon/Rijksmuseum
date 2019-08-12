package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.repositories.CollectionRepository
import com.rijksmuseum.sample.ui.models.CollectionItem
import kotlinx.coroutines.launch

class CollectionViewModel(private val collectionRepository: CollectionRepository) : ViewModel() {

    val collectionItems = MutableLiveData<List<CollectionItem>>()
    val loader = MutableLiveData<Boolean>()

    fun getCollection(maker: String) {
        viewModelScope.launch {
            loader.postValue(true)
            try {
                collectionItems.postValue(collectionRepository.getCollection(maker))
            } catch (e: Exception) {
                println(e)
            } finally {
                loader.postValue(false)
            }
        }
    }
}
