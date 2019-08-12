package com.rijksmuseum.sample.ui.fragments

import android.content.Context
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
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.databinding.FragmentEventListBinding
import com.rijksmuseum.sample.ui.ProgressBarListener
import com.rijksmuseum.sample.ui.adapters.BaseAdapter
import com.rijksmuseum.sample.ui.delegates.EventDelegateAdapter
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
    }
    private lateinit var progressBarListener: ProgressBarListener

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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is ProgressBarListener) {
            throw ClassCastException("Activity does not implement ProgressBarListener")
        }
        progressBarListener = context
    }

    override fun onResume() {
        super.onResume()
        viewModel.events.observe(this, Observer {
            baseAdapter.addDelegateUIModels(it)
        })
        viewModel.loader.observe(this, Observer {
            progressBarListener.displayProgressBar(it)
        })
        viewModel.getEvents(Calendar.getInstance().time)
    }

}
