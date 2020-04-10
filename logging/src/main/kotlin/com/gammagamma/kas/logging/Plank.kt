@file:Suppress("unused")
@file:SuppressLint("TimberExceptionLogging")

package com.gammagamma.kas.logging

import android.annotation.SuppressLint
import timber.log.Timber

object Plank {
    
    fun init() {
        
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        
    }
    
}

inline fun <reified T : Any> withPlankInternal(
    hashtag: String,
    message: String,
    block: (message: String) -> Unit
) { block("#$hashtag ⭬ $message") }

inline fun <reified T : Any?> withPlank(
    owner: T?,
    vararg messages: Any?,
    block: (message: String) -> Unit
) : T? {
    
    val message = messages.joinToString(", ")
    val hashtag = T::class.java.simpleName ?: "PLANK_NO_OWNER"
    
    //withPlankInternal<T>(hashtag, message, block)
    block("#$hashtag ⭬ $message")
    
    return owner
    
}

inline fun <reified T : Any> T.plankD(vararg messages: Any?) =
    withPlank(this, *messages) { Timber.d(it) }

inline fun <reified T : Any> T.plankW(vararg messages: Any?) =
    withPlank(this, *messages) { Timber.w(it) }

inline fun <reified T : Any> T.plankW(t: Throwable, vararg messages: Any?) =
    withPlank(this, *messages) { Timber.w(t, it) }

inline fun <reified T : Any> T.plankE(vararg messages: Any?) =
    withPlank(this, *messages) { Timber.e(it) }

inline fun <reified T : Any> T.plankE(t: Throwable, vararg messages: Any?) =
    withPlank(this, *messages) { Timber.e(t, it) }


//
inline fun <reified T : Any> T.plank(vararg messages: Any?) =
    plankD(*messages)
