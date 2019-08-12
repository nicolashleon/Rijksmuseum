package com.rijksmuseum.sample.repositories

import com.rijksmuseum.sample.network.services.CollectionService
import com.rijksmuseum.sample.ui.models.CollectionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class CollectionRepository(private val collectionService: CollectionService) {

    suspend fun getCollection(maker: String) = withContext(Dispatchers.IO) {
        val deferred = async { collectionService.getCollection(maker) }
        deferred.await().artObjects.map {
            CollectionItem(it.title, it.webImage.url)
        }
    }
}