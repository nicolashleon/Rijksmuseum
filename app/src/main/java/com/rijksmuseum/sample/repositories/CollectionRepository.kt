package com.rijksmuseum.sample.repositories

import com.rijksmuseum.sample.network.services.CollectionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CollectionRepository(private val collectionService: CollectionService) {

    suspend fun getCollection(maker: String) = withContext(Dispatchers.IO) {
        //TODO map the result to UI models and return it
        collectionService.getCollection(maker)
    }
}