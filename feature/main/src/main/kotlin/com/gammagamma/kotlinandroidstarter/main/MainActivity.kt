package com.gammagamma.kotlinandroidstarter.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.gammagamma.kotlinandroidstarter.main.databinding.ActivityMainBinding
import com.gammagamma.kotlinandroidstarter.ui.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    
    //region Class Members
    
    private val pagerAdapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        MainPagerAdapter(supportFragmentManager)
    }
    
    //endregion Class Members
    
    //region Lifecycle
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        /*if (is not signed in) {
            startActivity(Intent(this, LoginActivity::class.java))
            return
        }*/
    
        setupNavigation()
        
    }
    
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        
        if (data == null)
            return super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == RESULT_OK) {
            
            when (requestCode) {
                123 -> {}
            }
            
        }
    }
    
    //endregion Lifecycle
    
    //region Menus
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        when (item.itemId) {
            R.id.action_settings -> return true
        }
        
        return super.onOptionsItemSelected(item)
        
    }
    
    //endregion Menus
    
    //region Navigation
    
    private fun setupNavigation() {
        
        // Enable Toolbar as Actionbar
        setSupportActionBar(binding.toolbar)
        
        // Bottom tab navigation handler
        binding.bottomNavigation.setOnNavigationItemSelectedListener { changeFragment(it.itemId) }
        
        // Watch page swipes & update bottom nav to reflect changes
        binding.container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {
                
                val itemId = pagerAdapter.getItemId(position)
                if (itemId != null) binding.bottomNavigation.selectedItemId = itemId
                
            }
        })
        
        binding.container.adapter = pagerAdapter
        
    }
    
    private fun changeFragment(id: Int): Boolean {
        
        when (id) {
            R.id.navigation_page_a -> {
                binding.container.currentItem = 0
                binding.toolbar.title = getString(R.string.home_title)
                return true
            }
            R.id.navigation_page_b -> {
                binding.container.currentItem = 1
                binding.toolbar.title = getString(R.string.feed_title)
                return true
            }
            R.id.navigation_page_c -> {
                binding.container.currentItem = 2
                binding.toolbar.title = getString(R.string.about_title)
                return true
            }
        }
        
        return false
        
    }
    
    //endregion Navigation
    
    //region Permissions
    
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        /*if (requestCode == 123) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                infoDialog("Missing Permissions", "Without the requested permissions, " +
                    "this app cannot function.", { finish() })
        }*/
        
    }
    
    //endregion Permissions
    
}
