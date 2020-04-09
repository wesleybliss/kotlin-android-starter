package com.gammagamma.kotlinandroidstarter.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.gammagamma.kotlinandroidstarter.ui.extensions.injectBinding

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes protected val layoutId: Int
) : AppCompatActivity() {
    
    protected val binding by injectBinding<B>(layoutId)
    
}
