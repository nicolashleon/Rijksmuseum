package com.rijksmuseum.sample.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rijksmuseum.sample.BR
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.ui.models.CollectionItem
import com.rijksmuseum.sample.ui.models.DelegateUIModel
import com.squareup.picasso.Picasso

class CollectionDelegateAdapter : DelegateAdapter {

    companion object {
        @BindingAdapter("bind_imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            if (!imageUrl.isNullOrEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_photo)
                        .error(R.drawable.ic_photo)
                        .into(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                R.layout.item_collection, parent, false)
        return CollectionItemViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, delegateUIModel: DelegateUIModel) {
        if (viewHolder is CollectionItemViewHolder && delegateUIModel is CollectionItem) {
            viewHolder.binding.setVariable(BR.collection, delegateUIModel)
            viewHolder.binding.executePendingBindings()
        }
    }

    override fun areContentsTheSame(oldItem: DelegateUIModel, newItem: DelegateUIModel): Boolean {
        return oldItem is CollectionItem && newItem is CollectionItem && oldItem == newItem
    }

    inner class CollectionItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}