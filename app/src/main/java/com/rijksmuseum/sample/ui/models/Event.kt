package com.rijksmuseum.sample.ui.models

import java.util.*

data class Event(val name: String, val description: String, val price: Double,
                 val availableSeats: Int, val groupType: String, val startDate: Date,
                 val endDate: Date) : DelegateUIModel {
    override val viewType: Int = 2

    fun getPrice() : String = price.toString()
    fun getAvailableSeats() : String = availableSeats.toString()
}