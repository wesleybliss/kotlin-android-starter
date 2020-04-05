package com.kotlinandroidstarter.app.extensions

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <reified T : Any> Activity.injectActivity(vararg values: Any): Lazy<T> =
    inject(parameters = {
        parametersOf(
            *mutableListOf<Any>(this)
                .apply { addAll(values) }
                .toTypedArray()
        )
    })

inline fun <reified T : Any> Fragment.injectActivity(vararg values: Any): Lazy<T> =
    inject(parameters = {
        parametersOf(
            *mutableListOf<Any>(activity!!)
                .apply { addAll(values) }
                .toTypedArray()
        )
    })

/*inline fun <reified Plank : Any> Activity.injectBinding(@LayoutRes layoutResId: Int, vararg values: Any): Lazy<Plank> =
    injectActivity(
        *mutableListOf<Any>(layoutResId)
            .apply { addAll(values) }
            .toTypedArray()
    )*/

fun <T : ViewDataBinding> FragmentActivity.injectBinding(
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
