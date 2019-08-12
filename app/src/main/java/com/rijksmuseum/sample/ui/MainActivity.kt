package com.rijksmuseum.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.rijksmuseum.sample.R
import com.rijksmuseum.sample.databinding.ActivityMainBinding
import com.rijksmuseum.sample.ui.adapters.ViewPagerAdapter
import com.rijksmuseum.sample.ui.fragments.CollectionFragment
import com.rijksmuseum.sample.ui.fragments.EventListFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager).apply {
            addData(EventListFragment(), getString(R.string.title_events))
            addData(CollectionFragment(), getString(R.string.title_image_search))
        }
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

}
