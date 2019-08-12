package com.rijksmuseum.sample.ui.models

import androidx.annotation.StringRes

data class EmptyStateItem(@StringRes val message: Int) : DelegateUIModel {
    companion object {
        const val VIEW_TYPE = 0
    }

    override val viewType: Int = VIEW_TYPE
}