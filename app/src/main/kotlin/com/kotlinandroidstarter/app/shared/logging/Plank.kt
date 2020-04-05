@file:Suppress("unused")

package com.kotlinandroidstarter.app.shared.logging

import timber.log.Timber

inline fun <reified T : Any> withPlankInternal(
    hashtag: String,
    message: String,
    block: (message: String) -> Unit
) { block("#$hashtag ⭬ $message") }

inline fun <reified T : Any?> withPlank(
    owner: T?,
    vararg messages: String?,
    block: (message: String) -> Unit
) : T? {
    
    val message = messages.joinToString(", ")
    val hashtag = T::class.java.simpleName ?: "PLANK_NO_OWNER"
    
    //withPlankInternal<T>(hashtag, message, block)
    block("#$hashtag ⭬ $message")
    
    return owner
    
}

inline fun <reified T : Any> T.plankD(vararg messages: String?) =
    withPlank(this, *messages) { Timber.d(it) }

inline fun <reified T : Any> T.plankW(vararg messages: String?) =
    withPlank(this, *messages) { Timber.w(it) }

inline fun <reified T : Any> T.plankW(t: Throwable, vararg messages: String?) =
    withPlank(this, *messages) { Timber.w(t, it) }

inline fun <reified T : Any> T.plankE(vararg messages: String?) =
    withPlank(this, *messages) { Timber.e(it) }

inline fun <reified T : Any> T.plankE(t: Throwable, vararg messages: String?) =
    withPlank(this, *messages) { Timber.e(t, it) }


//
inline fun <reified T : Any> T.plank(vararg messages: String?) =
    plankD(*messages)
