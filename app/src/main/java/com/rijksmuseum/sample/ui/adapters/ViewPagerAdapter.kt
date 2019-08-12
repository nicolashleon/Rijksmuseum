package com.rijksmuseum.sample.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
        FragmentPagerAdapter(supportFragmentManager) {

    private val data = ArrayList<Pair<Fragment, String>>()

    companion object {
        private const val VIEW_PAGER_SIZE = 2
    }

    override fun getItem(position: Int): Fragment {
        return data[position].first
    }

    override fun getCount() = VIEW_PAGER_SIZE

    override fun getPageTitle(position: Int): CharSequence? {
        return data[position].second
    }

    fun addData(fragment: Fragment, title: String) {
        data.add(Pair(fragment, title))
    }

}