package com.gammagamma.kotlinandroidstarter.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gammagamma.kotlinandroidstarter.about.AboutFragment
import com.gammagamma.kotlinandroidstarter.feed.FeedFragment
import com.gammagamma.kotlinandroidstarter.home.HomeFragment
import com.gammagamma.logging.plank

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
