package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Exposition(
    val id: String,
    val name: String,
    val description: String,
    val code: String,
    val price: Price
)