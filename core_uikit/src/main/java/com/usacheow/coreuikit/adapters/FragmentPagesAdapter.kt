package com.usacheow.coreuikit.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentPagesAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList: MutableList<Pair<String, Fragment>> = mutableListOf()
) : FragmentPagerAdapter(fragmentManager) {

    override fun getPageTitle(position: Int) = fragmentList[position].first

    override fun getItem(position: Int) = fragmentList[position].second

    override fun getCount() = fragmentList.size

    fun addPage(fragment: Fragment, title: String = "") {
        fragmentList.add(Pair(title, fragment))
        notifyDataSetChanged()
    }
}