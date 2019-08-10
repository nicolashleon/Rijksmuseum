package com.rijksmuseum.sample.ui.adapters

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rijksmuseum.sample.ui.delegates.DelegateAdapter
import com.rijksmuseum.sample.ui.models.DelegateUIModel

/**
 * Code adapted from https://github.com/sockeqwe/AdapterDelegates
 * http://hannesdorfmann.com/android/adapter-delegates
 */
class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<DelegateUIModel>() {
        override fun areItemsTheSame(oldItem: DelegateUIModel, newItem: DelegateUIModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DelegateUIModel, newItem: DelegateUIModel): Boolean {
            return if(oldItem.viewType == newItem.viewType) {
                delegateAdapters.get(oldItem.viewType).areContentsTheSame(oldItem, newItem)
            } else {
                false
            }
        }

    }
    private var delegateAdapters = SparseArray<DelegateAdapter>()
    private val delegateUIModels = AsyncListDiffer(this, diffCallback)

    val isEmpty: Boolean = delegateUIModels.currentList.isEmpty()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegateAdapter = delegateAdapters.get(viewType)
        return delegateAdapter?.onCreateViewHolder(parent, viewType)!!
    }

    override fun getItemViewType(position: Int): Int {
        return delegateUIModels.currentList[position].viewType
    }

    override fun getItemCount() = delegateUIModels.currentList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegateAdapter = delegateAdapters.get(getItemViewType(position))
        delegateAdapter?.onBindViewHolder(holder, delegateUIModels.currentList[position])
    }

    fun <T : DelegateAdapter> addDelegate(delegateAdapter: T, viewType: Int) {
        delegateAdapters.put(viewType, delegateAdapter)
    }

}
