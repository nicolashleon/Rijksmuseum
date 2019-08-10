package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpositionType(
    val type: String,
    val guid: String,
    val friendlyName: String
)