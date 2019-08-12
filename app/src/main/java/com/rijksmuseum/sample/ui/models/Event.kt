package com.rijksmuseum.sample.ui.models

import java.util.*

data class Event(val name: String, val description: String, val price: Double,
                 val availableSeats: Int, val groupType: String, val startDate: Date,
                 val endDate: Date) : DelegateUIModel {

    companion object {
        const val VIEW_TYPE = 2
    }

    override val viewType = VIEW_TYPE

    fun getPrice(): String = price.toString()
    fun getAvailableSeats(): String = availableSeats.toString()
}