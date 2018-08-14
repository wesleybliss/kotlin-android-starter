package com.kotlinandroidstarter.app.adapters

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.fragments.AFragment
import com.kotlinandroidstarter.app.fragments.BFragment
import com.kotlinandroidstarter.app.fragments.CFragment
import timber.log.Timber


class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    
    override fun getItem(position: Int): Fragment? {
        
        when (position) {
            0 -> return AFragment()
            1 -> return BFragment()
            2 -> return CFragment()
        }
        
        Timber.e("Invalid page")
        //Toast.makeText(this@MainActivity, "Invalid page", Toast.LENGTH_SHORT).show()
        return null
        
    }
    
    override fun getCount(): Int {
        return 3
    }
    
    @IdRes fun getItemId(position: Int) : Int? {
        
        when (position) {
            0 -> return R.id.navigation_page_a
            1 -> return R.id.navigation_page_b
            2 -> return R.id.navigation_page_c
        }
        
        return null
        
    }
    
}
