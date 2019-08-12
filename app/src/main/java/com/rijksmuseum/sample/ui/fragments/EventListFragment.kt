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
import com.rijksmuseum.sample.databinding.FragmentEventListBinding
import com.rijksmuseum.sample.ui.adapters.BaseAdapter
import com.rijksmuseum.sample.ui.delegates.EmptyStateDelegateAdapter
import com.rijksmuseum.sample.ui.delegates.EventDelegateAdapter
import com.rijksmuseum.sample.ui.show
import com.rijksmuseum.sample.ui.models.EmptyStateItem
import com.rijksmuseum.sample.ui.models.Event
import com.rijksmuseum.sample.ui.viewmodels.EventListViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class EventListFragment : Fragment() {

    companion object {
        const val TAG = "EventListFragment"
        fun newInstance() = EventListFragment()
    }

    private lateinit var binding: FragmentEventListBinding
    private val viewModel: EventListViewModel by viewModel()
    private val baseAdapter = BaseAdapter().apply {
        addDelegate(EventDelegateAdapter(), Event.VIEW_TYPE)
        addDelegate(EmptyStateDelegateAdapter(), EmptyStateItem.VIEW_TYPE)
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container,
                false)
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
        viewModel.events.observe(this, Observer { result ->
            result.value?.let {
                val list = if (it.isEmpty()) {
                    listOf(EmptyStateItem(R.string.txt_empty_state_event_list))
                } else {
                    it
                }
                baseAdapter.addDelegateUIModels(list)
            }
            result.error?.let {
                baseAdapter.addDelegateUIModels(emptyList())
                Snackbar.make(binding.root, R.string.txt_error_event_list, Snackbar.LENGTH_LONG)
                        .setAction(R.string.txt_retry) {
                            getNextWeekEvents()
                        }
                        .show()
            }

        })
        viewModel.loader.observe(this, Observer {
            binding.progressBar.show(it)
        })
        getNextWeekEvents()

    }

    private fun getNextWeekEvents() {
        viewModel.getNextWeekEvents(Calendar.getInstance().time)
    }

}
