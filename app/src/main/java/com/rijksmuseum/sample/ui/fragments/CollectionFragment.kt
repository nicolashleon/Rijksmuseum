package com.rijksmuseum.sample.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.databinding.FragmentItemCollectionListBinding
import com.rijksmuseum.sample.ui.Result
import com.rijksmuseum.sample.ui.adapters.BaseAdapter
import com.rijksmuseum.sample.ui.delegates.CollectionDelegateAdapter
import com.rijksmuseum.sample.ui.delegates.EmptyStateDelegateAdapter
import com.rijksmuseum.sample.ui.models.CollectionItem
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import com.rijksmuseum.sample.ui.show
import com.rijksmuseum.sample.ui.viewmodels.CollectionViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class CollectionFragment : Fragment() {

    companion object {
        const val TAG = "CollectionFragment"
        fun newInstance() = CollectionFragment()
    }

    private lateinit var binding: FragmentItemCollectionListBinding
    private val baseAdapter = BaseAdapter().apply {
        addDelegate(CollectionDelegateAdapter(), CollectionItem.VIEW_TYPE)
        addDelegate(EmptyStateDelegateAdapter(), EmptyStateItem.VIEW_TYPE)
    }

    private lateinit var recyclerView: RecyclerView
    private val viewModel: CollectionViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_collection_list,
                container, false)
        recyclerView = binding.recyclerView
        recyclerView.adapter = baseAdapter
        val layoutManager = LinearLayoutManager(binding.root.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,
                layoutManager.orientation))
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.collectionItems.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> result.data?.let {
                    binding.progressBar.show(false)
                    baseAdapter.addDelegateUIModels(it)
                }
                is Result.Error -> {
                    baseAdapter.addDelegateUIModels(emptyList())
                    binding.progressBar.show(false)
                    Snackbar.make(binding.root, R.string.txt_error_collection_item_list,
                            Snackbar.LENGTH_LONG)
                            .setAction(R.string.txt_retry) {
                                getCollection()
                            }
                            .show()
                }
                is Result.Loading -> binding.progressBar.show(true)
            }

        })

        getCollection()

    }

    private fun getCollection() {
        viewModel.getCollection("Rembrandt")
    }


}
