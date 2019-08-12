package com.rijksmuseum.sample.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rijksmuseum.sample.BR
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import com.rijksmuseum.sample.ui.models.Event

class EmptyStateDelegateAdapter : DelegateAdapter {

    companion object {
        @BindingAdapter("bind_message")
        @JvmStatic
        fun bindMessage(textView: TextView, emptyStateItem: EmptyStateItem) {
            textView.setText(emptyStateItem.message)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                R.layout.item_empty_state, parent, false)
        return EmptyStateViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, delegateUIModel: DelegateUIModel) {
        if (viewHolder is EmptyStateViewHolder && delegateUIModel is EmptyStateItem) {
            viewHolder.binding.setVariable(BR.emptyStateItem, delegateUIModel)
            viewHolder.binding.executePendingBindings()
        }
    }

    override fun areContentsTheSame(oldItem: DelegateUIModel, newItem: DelegateUIModel): Boolean {
        return oldItem is Event && newItem is Event && oldItem == newItem
    }

    inner class EmptyStateViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}