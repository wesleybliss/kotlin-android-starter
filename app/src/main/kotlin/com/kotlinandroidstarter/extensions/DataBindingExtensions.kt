package com.kotlinandroidstarter.extensions

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.kotlinandroidstarter.app.App

inline fun <T> mutableLiveDataOf(initialValue: T?, defaultValue: T? = null, post: Boolean = false) =
    MutableLiveData<T>().apply {
        if (!post) value = initialValue ?: defaultValue
        else postValue(initialValue ?: defaultValue)
    }

inline fun <T> dependantLiveData(vararg dependencies: LiveData<T>, crossinline mapper: () -> T?) =
    MediatorLiveData<T>().also { mediatorLiveData ->
        val observer = Observer<T> { mediatorLiveData.value = mapper() }
        dependencies.forEach { dependencyLiveData ->
            mediatorLiveData.addSource<T>(dependencyLiveData, observer)
        }
    }

fun ViewModel.getString(@StringRes resourceId: Int) : String =
    android.content.res.Resources.getSystem().getString(resourceId)

@ColorInt
fun ViewModel.getColor(@ColorRes resourceId: Int) : Int =
    ContextCompat.getColor(App.instance.applicationContext, resourceId)
