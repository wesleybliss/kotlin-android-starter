package com.gammagamma.kas.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gammagamma.kas.about.AboutFragment
import com.gammagamma.kas.feed.FeedFragment
import com.gammagamma.kas.home.HomeFragment
import com.gammagamma.kas.logging.plank

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    
    private val items by lazy {
        listOf<Fragment>(
            HomeFragment(),
            FeedFragment(),
            AboutFragment()
        )
    }
    
    override fun createFragment(position: Int): Fragment =
        items[position].apply { plank("Creating frag @ pos $position") }
    
    override fun getItemCount(): Int = items.size
    
}
