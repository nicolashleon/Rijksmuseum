package com.rijksmuseum.sample.ui.models

data class Event(val name: String, val description: String, val price: Double,
                 val availableSeats: Int, val groupType: String, val startDate: String,
                 val endDate: String) : DelegateUIModel {
    override val viewType: Int = 2
}