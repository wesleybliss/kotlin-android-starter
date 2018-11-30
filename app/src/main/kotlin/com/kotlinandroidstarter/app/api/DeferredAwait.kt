package com.kotlinandroidstarter.app.api

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume

suspend fun <T : Any> Deferred<Response<T>>.awaitResult(): Result<T> =
    suspendCancellableCoroutine { continuation ->
        
        launch {
            try {
                
                val response = await()
                val next =
                    if (response.isSuccessful)
                        response.body()
                            ?.let { Result.Success(it) }
                            ?: (
                                if (response.code() ==200) Result.Error(Exception("body is empty"))
                                else Result.Error(NullPointerException("Response body is null"))
                            )
                    else
                        Result.Error(HttpException(response))
                
                continuation.resume(next)
                
            }
            catch (e: Throwable){
                //  Log.e("DeferredAwait", e.message)
                continuation.resume(Result.Error(e))
            }
            
        }
        
        registerOnCompletion(continuation)
        
    }

private fun Deferred<Response<*>>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    // continuation.invokeOnCompletion {
    continuation.invokeOnCancellation {
        if (continuation.isCancelled)
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
                ex.printStackTrace()
            }
    }
}
