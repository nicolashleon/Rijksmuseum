package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.repositories.CollectionRepository
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import kotlinx.coroutines.launch

class CollectionViewModel(private val collectionRepository: CollectionRepository) : ViewModel() {

    val collectionItems = MutableLiveData<List<DelegateUIModel>>()
    val loader = MutableLiveData<Boolean>()

    fun getCollection(maker: String) {
        viewModelScope.launch {
            loader.postValue(true)
            try {
                val collectionItemList = collectionRepository.getCollection(maker)
                if (collectionItemList.isNotEmpty()) {
                    collectionItems.postValue(collectionItemList)
                } else {
                    collectionItems.postValue(listOf(EmptyStateItem(R.string.txt_empty_state_collection_item_list)))
                }

            } catch (e: Exception) {
                println(e)
                collectionItems.postValue(emptyList())
            } finally {
                loader.postValue(false)
            }
        }
    }
}
