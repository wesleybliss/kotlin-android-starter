package com.kotlinandroidstarter.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import com.kotlinandroidstarter.app.R
import com.kotlinandroidstarter.app.adapters.MainPagerAdapter

class MainActivity : AppCompatActivity() {
    
    //region Class Members
    
    private val pagerAdapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        MainPagerAdapter(supportFragmentManager)
    }
    
    //endregion Class Members
    
    //region Lifecycle
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
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
        if (toolbar != null) setSupportActionBar(toolbar)
        
        // Bottom tab navigation handler
        bottomNavigation.setOnNavigationItemSelectedListener { item -> changeFragment(item.itemId) }
        
        // Watch page swipes & update bottom nav to reflect changes
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {
            
                val itemId = pagerAdapter.getItemId(position)
                if (itemId != null) bottomNavigation.selectedItemId = itemId
            
            }
        })
        
        container.adapter = pagerAdapter
        
    }
    
    private fun changeFragment(id: Int): Boolean {
        
        when (id) {
            R.id.navigation_page_a -> {
                container.currentItem = 0
                toolbar.title = getString(R.string.title_page_a)
                return true
            }
            R.id.navigation_page_b -> {
                container.currentItem = 1
                toolbar.title = getString(R.string.title_page_b)
                return true
            }
            R.id.navigation_page_c -> {
                container.currentItem = 2
                toolbar.title = getString(R.string.title_page_c)
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
