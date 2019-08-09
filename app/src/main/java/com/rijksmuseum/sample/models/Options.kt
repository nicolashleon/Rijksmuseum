package com.rijksmuseum.sample.models

data class Options(
    val links: Links,
    val id: String,
    val lang: String,
    val date: String,
    val period: Period,
    val exposition: Exposition,
    val groupType: GroupType,
    val pageRef: PageRef,
    val expositionType: ExpositionType
)