package com.rijksmuseum.sample.models

data class Period(
    val id: String,
    val startDate: String,
    val endDate: String,
    val current: Int,
    val maximum: Int,
    val remaining: Int,
    val code: String,
    val text: String
)