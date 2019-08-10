package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageRef(
    val title: String,
    val url: String
)