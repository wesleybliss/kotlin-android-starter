package com.gammagamma.kas.ui.extensions

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

fun <T : ViewDataBinding> AppCompatActivity.injectBinding(
    @LayoutRes layoutResId: Int, autoLifecycleOwner: Boolean = true)
    : Lazy<T> = lazy {
    val binding = DataBindingUtil.setContentView<T>(this, layoutResId)
    if (autoLifecycleOwner) binding.lifecycleOwner = this
    binding
}

inline fun <reified T : ViewDataBinding> FragmentActivity.inflateBinding(
    @LayoutRes layoutResId: Int, autoLifecycleOwner: Boolean = true)
    : Lazy<T> = lazy {
    val binding = DataBindingUtil.inflate<T>(layoutInflater, layoutResId, null, false)
    if (autoLifecycleOwner) binding.lifecycleOwner = this
    binding
}
