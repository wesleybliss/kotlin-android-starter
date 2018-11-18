package com.kotlinandroidstarter.extensions

import androidx.lifecycle.MutableLiveData

/**
 * Easily set default/initial value of a [MutableLiveData]
 */
fun <T : Any?> MutableLiveData<T>.default(initialValue: T?, defaultValue: T? = null) =
    apply { value = initialValue ?: defaultValue }

/**
 * Easily set default/initial value of a [MutableLiveData]
 */
fun <T : Any?> MutableLiveData<T>.defaultPost(initialValue: T?, defaultValue: T? = null) =
    apply { postValue(initialValue ?: defaultValue) }
