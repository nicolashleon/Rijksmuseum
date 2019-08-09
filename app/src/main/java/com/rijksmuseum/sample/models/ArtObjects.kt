package com.rijksmuseum.sample.models

data class ArtObjects(
    val links: Links,
    val id: String,
    val objectNumber: String,
    val title: String,
    val hasImage: Boolean,
    val principalOrFirstMaker: String,
    val longTitle: String,
    val showImage: Boolean,
    val permitDownload: Boolean,
    val webImage: WebImage,
    val headerImage: HeaderImage,
    val productionPlaces: List<String>
)