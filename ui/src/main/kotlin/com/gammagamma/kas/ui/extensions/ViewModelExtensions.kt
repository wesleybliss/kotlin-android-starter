@file:Suppress("unused")

package com.gammagamma.kas.ui.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

/**
 * Shorthand to create a new [LiveData] instance
 *
 * @param T Data type for value
 * @param initialValue Initial value
 * @param defaultValue Default value, if initial is null
 * @param post If postValue should be used instead of setValue
 * @param fn Callback to do initial setup, fetch data, etc.
 *           This is scoped to [MutableLiveData] so we have the
 *           opportunity to manipulate the LiveData before it becomes immutable
 * @return [LiveData] of [T] after the [fn] has applied any changes
 */
inline fun <reified T> liveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false,
    noinline fn: (MutableLiveData<T>.() -> Unit)? = null
) : LiveData<T> {
    val instance = mutableLiveDataOf(initialValue, defaultValue, post)
    fn?.invoke(instance)
    return instance
}

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false,
    noinline fn: (() -> Unit)? = null
) = MutableLiveData<T>().apply {
    if (!post) value = initialValue ?: defaultValue
    else postValue(initialValue ?: defaultValue)
    fn?.invoke()
}

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    post: Boolean = false,
    noinline fn: (() -> Unit)? = null
) = mutableLiveDataOf(initialValue, initialValue, post, fn)

inline fun <reified T> mutableLiveDataOf(
    initialValue: T?,
    noinline fn: (() -> Unit)? = null
) = mutableLiveDataOf(initialValue, initialValue, false, fn)

inline fun <reified T> mediatorLiveDataOf(
    initialValue: T?,
    defaultValue: T? = null,
    post: Boolean = false,
    noinline block: (MediatorLiveData<T>.() -> Unit)? = null
) = MediatorLiveData<T>().apply {
    if (!post) value = initialValue ?: defaultValue
    else postValue(initialValue ?: defaultValue)
    block?.invoke(this)
}

fun <T, S> MediatorLiveData<T>.addSources(vararg sources: LiveData<S>, onChanged: (S) -> Unit) {
    sources.forEach { source ->
        addSource(source) { onChanged(it) }
    }
}
