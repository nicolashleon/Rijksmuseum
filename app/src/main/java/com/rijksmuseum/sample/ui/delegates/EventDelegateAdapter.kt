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
import com.rijksmuseum.sample.ui.models.Event
import java.text.DateFormat

class EventDelegateAdapter : DelegateAdapter {

    companion object {
        @BindingAdapter("bind_dates")
        @JvmStatic
        fun bindDates(textView: TextView, event: Event) {
            val formatter = DateFormat.getDateTimeInstance()
            val startDate = formatter.format(event.startDate)
            val endDate = formatter.format(event.endDate)
            textView.text = textView.context.getString(R.string.txt_event_date_range, startDate,
                    endDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                R.layout.item_event, parent, false)
        return EventViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, delegateUIModel: DelegateUIModel) {
        if (viewHolder is EventViewHolder && delegateUIModel is Event) {
            viewHolder.binding.setVariable(BR.event, delegateUIModel)
            viewHolder.binding.executePendingBindings()
        }
    }

    override fun areContentsTheSame(oldItem: DelegateUIModel, newItem: DelegateUIModel): Boolean {
        return oldItem is Event && newItem is Event && oldItem == newItem
    }

    inner class EventViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}