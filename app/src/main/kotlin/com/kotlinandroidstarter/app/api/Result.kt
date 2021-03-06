package com.kotlinandroidstarter.app.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber
import java.io.IOException

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

}

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Result<T>,
    errorMessage: String
) : Deferred<Result<T>> = GlobalScope.async(Dispatchers.Main) {
    try {
        val res = call()
        if (res is Result.Success) res
        else throw Exception("Empty response")
    } catch (e: Exception) {
        Timber.e(e)
        Result.Error(IOException(errorMessage, e))
    }
}

val <T> T.exhaustive: T
    get() = this
