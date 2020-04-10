package com.gammagamma.kotlinandroidstarter.ui.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

inline fun <T : Any> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit) {
    observe(owner, Observer {
        if (it != null)
            observer(it)
    })
}

inline fun <T : Any> MutableLiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) = (this as LiveData<T>).observe(owner, observer)
