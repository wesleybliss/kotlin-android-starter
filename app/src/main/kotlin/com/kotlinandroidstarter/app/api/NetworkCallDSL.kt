package com.kotlinandroidstarter.app.api

import androidx.lifecycle.MutableLiveData
import com.kotlinandroidstarter.extensions.async
import com.kotlinandroidstarter.extensions.mutableLiveDataOf
import kotlinx.coroutines.Deferred
import retrofit2.Response
import timber.log.Timber

class CallHandler<T: Any> {
    
    lateinit var request: Deferred<Response<T>>
    
    suspend fun fetch() : Deferred<MutableLiveData<Result<T>>> = async {
        
        val result = mutableLiveDataOf<Result<T>>(Result.Loading())
        
        try {
            val response = request.awaitResult() //.getOrThrow()
            result.postValue(response)
        }
        catch (e: Exception) {
            // e.handleException(result)
            Timber.e(e)
            result.postValue(Result.Error(e))
        }
        
        result
        
    }
}

suspend fun <T: Any> apiCall(block: CallHandler<T>.() -> Unit)
    : Deferred<MutableLiveData<Result<T>>> =
    CallHandler<T>().apply(block).fetch()
