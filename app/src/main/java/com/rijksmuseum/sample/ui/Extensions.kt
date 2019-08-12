package com.rijksmuseum.sample.ui

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show(show: Boolean) {
    visibility = if(show) View.VISIBLE else View.GONE
}