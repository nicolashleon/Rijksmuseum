package com.rijksmuseum.sample.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.repositories.CollectionRepository
import com.rijksmuseum.sample.ui.Result
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import kotlinx.coroutines.launch

class CollectionViewModel(private val collectionRepository: CollectionRepository) : ViewModel() {

    val collectionItems = MutableLiveData<Result<List<DelegateUIModel>>>()

    fun getCollection(maker: String) {
        viewModelScope.launch {
            collectionItems.postValue(Result.Loading())
            try {
                var collectionItemList: List<DelegateUIModel> = collectionRepository.getCollection(maker)
                if (collectionItemList.isEmpty()) {
                    collectionItemList = listOf(EmptyStateItem(R.string.txt_empty_state_collection_item_list))
                }
                collectionItems.postValue(Result.Success(collectionItemList))
            } catch (e: Exception) {
                println(e)
                collectionItems.postValue(Result.Error(throwable = e))
            }
        }
    }
}
