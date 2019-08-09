package com.rijksmuseum.sample.models

data class CollectionResponse(
    val elapsedMilliseconds: Int,
    val count: Int,
    val artObjects: List<ArtObjects>
)