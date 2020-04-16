package com.gammagamma.kas.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.gammagamma.kas.logging.plank
import com.gammagamma.kas.ui.extensions.injectBinding
import com.google.android.material.appbar.AppBarLayout

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes protected val layoutId: Int
) : AppCompatActivity() {
    
    protected val binding by injectBinding<B>(layoutId)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        // Touch the binding here to make sure
        // it loads before anything else, or NPEs happen
        plank(binding)
        
    }
    
}
