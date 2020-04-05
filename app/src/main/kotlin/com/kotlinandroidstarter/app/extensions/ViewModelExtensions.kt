package com.kotlinandroidstarter.app.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

inline fun <reified T> liveDataOf(initialValue: T?, defaultValue: T? = null, post: Boolean = false) : LiveData<T> =
    mutableLiveDataOf(initialValue, defaultValue, post)

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false) =
    MutableLiveData<T>().apply {
        if (!post) value = initialValue ?: defaultValue
        else postValue(initialValue ?: defaultValue)
    }

inline fun <reified T> mediatorLiveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false,
    crossinline fn: MediatorLiveData<T>.() -> Unit) =
    MediatorLiveData<T>().apply {
        if (!post) value = initialValue ?: defaultValue
        else postValue(initialValue ?: defaultValue)
        fn()
    }
