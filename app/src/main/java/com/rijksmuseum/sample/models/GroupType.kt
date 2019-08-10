package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupType(
    val type: String,
    val guid: String,
    val friendlyName: String
)