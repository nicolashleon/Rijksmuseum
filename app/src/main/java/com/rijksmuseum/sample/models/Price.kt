package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
        val id: String,
        val calculationType: Int,
        val amount: Double
)