package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeaderImage(
    val guid: String,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val width: Int,
    val height: Int,
    val url: String
)