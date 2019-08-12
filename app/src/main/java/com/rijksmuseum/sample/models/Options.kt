package com.rijksmuseum.sample.models

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Options(
        val links: Links,
        val id: String,
        val lang: String,
        val date: Date,
        val period: Period,
        val exposition: Exposition,
        val groupType: GroupType,
        val pageRef: PageRef,
        val expositionType: ExpositionType
)