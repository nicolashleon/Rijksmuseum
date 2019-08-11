package com.rijksmuseum.sample.ui.models

data class Collection(val name: String, val imageUrl: String) : DelegateUIModel {
    override val viewType: Int = 1
}