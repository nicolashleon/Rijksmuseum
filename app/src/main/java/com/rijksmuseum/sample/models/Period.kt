package com.rijksmuseum.sample.models

import java.util.*

data class Period(
        val id: String,
        val startDate: Date,
        val endDate: Date,
        val current: Int,
        val maximum: Int,
        val remaining: Int,
        val code: String?,
        val text: String
)