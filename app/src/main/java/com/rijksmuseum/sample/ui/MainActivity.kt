package com.rijksmuseum.sample.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.databinding.ActivityMainBinding
import com.rijksmuseum.sample.ui.fragments.CollectionFragment
import com.rijksmuseum.sample.ui.fragments.EventListFragment

class MainActivity : AppCompatActivity(), ProgressBarListener {

    private val collectionFragment = CollectionFragment.newInstance()
    private val eventListFragment = EventListFragment.newInstance()
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_events -> {
                replaceFragment(EventListFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_image_search -> {
                replaceFragment(CollectionFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        addFragment(eventListFragment, EventListFragment.TAG)
    }

    private fun addFragment(fragment : Fragment, tag : String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_layout, fragment, tag)
        transaction.commitNow()
    }

    private fun replaceFragment(fragmentTag : String) {

        val isFragmentAlreadyAdded = supportFragmentManager.findFragmentByTag(fragmentTag) != null
        if(fragmentTag == EventListFragment.TAG && !isFragmentAlreadyAdded) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(collectionFragment)
            transaction.add(R.id.fragment_layout, eventListFragment, EventListFragment.TAG)
            transaction.commitNow()
        } else if (fragmentTag == CollectionFragment.TAG && !isFragmentAlreadyAdded) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(eventListFragment)
            transaction.add(R.id.fragment_layout, collectionFragment, CollectionFragment.TAG)
            transaction.commitNow()
        }

    }
    override fun displayProgressBar(display: Boolean) {
        binding.progressBar.visibility = if(display) View.VISIBLE else View.GONE
    }
}
