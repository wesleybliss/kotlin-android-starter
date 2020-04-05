package com.kotlinandroidstarter.app.adapters

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.features.home.HomeFragment
import com.kotlinandroidstarter.app.features.feed.FeedFragment
import com.kotlinandroidstarter.app.features.about.AboutFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> HomeFragment()
            1 -> FeedFragment()
            2 -> AboutFragment()
            else -> HomeFragment()
        }
    
    override fun getCount(): Int {
        return 3
    }
    
    @IdRes fun getItemId(position: Int) : Int? =
        when (position) {
            0 -> R.id.navigation_page_a
            1 -> R.id.navigation_page_b
            2 -> R.id.navigation_page_c
            else -> null
        }
    
}
