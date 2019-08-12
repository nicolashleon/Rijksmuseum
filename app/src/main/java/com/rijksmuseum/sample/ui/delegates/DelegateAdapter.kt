package com.rijksmuseum.sample.ui.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rijksmuseum.sample.ui.models.DelegateUIModel

/**
 * Code adapted from https://github.com/sockeqwe/AdapterDelegates
 * http://hannesdorfmann.com/android/adapter-delegates
 */
interface DelegateAdapter {
    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, delegateUIModel: DelegateUIModel)
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun areContentsTheSame(oldItem: DelegateUIModel, newItem: DelegateUIModel): Boolean
}