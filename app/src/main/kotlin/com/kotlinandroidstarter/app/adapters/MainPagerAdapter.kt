package com.kotlinandroidstarter.app.adapters

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.fragments.AFragment
import com.kotlinandroidstarter.app.fragments.BFragment
import com.kotlinandroidstarter.app.fragments.CFragment
import timber.log.Timber

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> AFragment()
            1 -> BFragment()
            2 -> CFragment()
            else -> AFragment()
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
