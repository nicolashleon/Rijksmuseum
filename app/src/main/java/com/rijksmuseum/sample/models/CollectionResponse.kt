package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionResponse(
    val elapsedMilliseconds: Int,
    val count: Int,
    val artObjects: List<ArtObjects>
)