package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.repositories.CollectionRepository
import com.rijksmuseum.sample.ui.Result
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import kotlinx.coroutines.launch

class CollectionViewModel(private val collectionRepository: CollectionRepository) : ViewModel() {

    val collectionItems = MutableLiveData<Result<List<DelegateUIModel>>>()
    val loader = MutableLiveData<Boolean>()

    fun getCollection(maker: String) {
        viewModelScope.launch {
            loader.postValue(true)
            try {
                val collectionItemList = collectionRepository.getCollection(maker)
                collectionItems.postValue(Result(collectionItemList))
            } catch (e: Exception) {
                println(e)
                collectionItems.postValue(Result(e))
            } finally {
                loader.postValue(false)
            }
        }
    }
}
