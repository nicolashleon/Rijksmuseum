package com.rijksmuseum.sample.ui.models

data class CollectionItem(val name: String, val imageUrl: String) : DelegateUIModel {
    companion object {
        const val VIEW_TYPE = 1
    }

    override val viewType: Int = VIEW_TYPE
}