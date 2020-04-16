package com.gammagamma.kas.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.gammagamma.kas.logging.plank

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseToolbarActivity <B : ViewDataBinding>(
    @LayoutRes layoutId: Int
) : BaseActivity<B>(layoutId) {
    
    /**
     * If a layout has a toolbar, find it here, since <include>
     * are not accessible from the instantiated binding classes
     */
    private val bindingToolbar: Toolbar? by lazy { binding.root.findViewById<Toolbar?>(R.id.toolbar) ?: null }
    
    /**
     * Shortcut for not having to null check the binding toolbar
     * Only use if you're sure the layout has app_bar.xml included in the layout
     */
    protected val toolbar by lazy { bindingToolbar!! }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        setupAppBar(toolbar)
        
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
    
    protected fun setupAppBar(toolbar: Toolbar) {
        
        setSupportActionBar(toolbar)
        
        supportActionBar!!.apply {
            setHomeButtonEnabled(true)
            //setDisplayHomeAsUpEnabled(true)
        }
        
    }
    
}
