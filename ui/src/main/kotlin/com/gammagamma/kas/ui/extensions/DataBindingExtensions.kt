package com.gammagamma.kas.ui.extensions

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : ViewDataBinding> AppCompatActivity.injectBinding(
    @LayoutRes layoutResId: Int,
    autoLifecycleOwner: Boolean = true
) = lazy<T> {
    val binding = DataBindingUtil.setContentView<T>(this, layoutResId)
    if (autoLifecycleOwner) binding.lifecycleOwner = this
    binding
}

inline fun <reified T : ViewDataBinding> FragmentActivity.inflateBinding(
    @LayoutRes layoutResId: Int,
    autoLifecycleOwner: Boolean = true
) = lazy {
    val binding = DataBindingUtil.inflate<T>(layoutInflater, layoutResId, null, false)
    if (autoLifecycleOwner) binding.lifecycleOwner = this
    binding
}
