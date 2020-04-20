package com.gammagamma.kas.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.gammagamma.kas.main.databinding.ActivityMainBinding
import com.gammagamma.kas.navigation.Navigation.launchKitchenSinkActivity
import com.gammagamma.kas.navigation.Navigation.launchSettingsActivity
import com.gammagamma.kas.ui.BaseActivity
import com.gammagamma.kas.ui.BaseToolbarActivity
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class MainActivity : BaseToolbarActivity<ActivityMainBinding>(R.layout.activity_main) {
    
    //region Class Members
    
    private val vm: MainViewModel by viewModel()
    private val pagerAdapter by lazy { MainPagerAdapter(this) }
    
    //endregion Class Members
    
    //region Lifecycle
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        binding.vm = vm
        
        setupNavigation()
        
    }
    
    //endregion Lifecycle
    
    //region Menus
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        when (item.itemId) {
            R.id.action_kitchen_sink -> return true.apply { this@MainActivity.launchKitchenSinkActivity() }
            R.id.action_settings -> return true.apply { this@MainActivity.launchSettingsActivity() }
        }
        
        return super.onOptionsItemSelected(item)
        
    }
    
    //endregion Menus
    
    //region Navigation
    
    private fun setupNavigation() {
        
        setSupportActionBar(toolbar)
        
        binding.bottomNavigation.setOnNavigationItemSelectedListener { changeFragment(it.itemId) }
        
        binding.viewPagerMain.adapter = pagerAdapter
        binding.viewPagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val itemId = pagerAdapter.getItemId(position)
                binding.bottomNavigation.selectedItemId = itemId.toInt()
            }
        })
        
    }
    
    private fun changeFragment(id: Int): Boolean {
        
        when (id) {
            R.id.navigation_page_a -> {
                binding.viewPagerMain.currentItem = 0
                toolbar.title = getString(R.string.home_title)
                return true
            }
            R.id.navigation_page_b -> {
                binding.viewPagerMain.currentItem = 1
                toolbar.title = getString(R.string.feed_title)
                return true
            }
            R.id.navigation_page_c -> {
                binding.viewPagerMain.currentItem = 2
                toolbar.title = getString(R.string.about_title)
                return true
            }
        }
        
        return false
        
    }
    
    //endregion Navigation
    
}
