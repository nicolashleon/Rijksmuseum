package com.rijksmuseum.sample.models

data class Exposition(
    val id: String,
    val name: String,
    val description: String,
    val code: String,
    val price: Price
)