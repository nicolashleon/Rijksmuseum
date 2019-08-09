package com.rijksmuseum.sample.models

data class HeaderImage(
    val guid: String,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val width: Int,
    val height: Int,
    val url: String
)