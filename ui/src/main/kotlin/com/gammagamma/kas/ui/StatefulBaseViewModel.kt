package com.gammagamma.kas.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.gammagamma.kas.domain.net.Result
import com.gammagamma.kas.resources.error.filterError
import com.gammagamma.kas.ui.extensions.addSources
import com.gammagamma.kas.ui.extensions.mediatorLiveDataOf
import com.gammagamma.kas.logging.plank
import com.gammagamma.kas.ui.observable.ConnectivityLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.Instant
import kotlin.reflect.KSuspendFunction0

/**
 * Base, native, Android Architecture Components ViewModel,
 * useful to extend for any ViewModel that needs error & loading states
 */
abstract class StatefulBaseViewModel(isNetworkAware: Boolean? = false) : BaseViewModel() {
    
    @Suppress("unused")
    open var networkAware = isNetworkAware ?: false
        set(value) {
            field = value
            if (value) {
                offline.value = !ConnectivityLiveData.isOnline
                offline.addSource(ConnectivityLiveData.instance) { isOnline ->
                    offline.value = !isOnline
                }
            } else {
                offline.value = false
                offline.removeSource(ConnectivityLiveData.instance)
            }
        }
    
    open var error = mediatorLiveDataOf<String>(null)
    open var loading = mediatorLiveDataOf(false)
    
    open val offline = mediatorLiveDataOf(false)
    
    open val hasError = mediatorLiveDataOf(false) {
        addSource(error) {
            postValue(!it.isNullOrBlank())
        }
    }
    
    open val ready = mediatorLiveDataOf(false) {
        addSources(loading, hasError, offline) {
            value = try {
                loading.value == false &&
                    error.value.isNullOrBlank() &&
                    offline.value == false
            } catch (e: Exception) { false }
        }
    }
    
    @Suppress("unused")
    fun observeError(owner: LifecycleOwner, done: (error: String) -> Unit) {
        error.observe(owner, Observer { 
            if (it.isNotBlank()) done(it)
        })
    }
    
    @Suppress("unused")
    fun observeLoading(owner: LifecycleOwner, done: (loading: Boolean) -> Unit) {
        loading.observe(owner, Observer { 
            done(it)
        })
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun <T> storageRequest(
        onRequest: KSuspendFunction0<Flow<T>>,
        onError: (t: Throwable) -> Unit,
        onSuccess: (data: T?) -> Unit
    ) {
        
        viewModelScope.launch {
            
            val result = onRequest()
            
            /*if (result is Result.Error) {
                
                error.postValue(filterError(result.exception).message)
                onError(result.exception)
                
                return@launch
                
            }*/
            
            error.postValue(null)
            loading.postValue(false)
            
            //onSuccess(result)
            
        }
        
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun <T> storageRequest(
        onRequest: KSuspendFunction0<Flow<T>>,
        onSuccess: (data: T?) -> Unit
    ) = storageRequest(
        onRequest = onRequest,
        onError = {},
        onSuccess = onSuccess
    )
    
    /**
     * Makes a network/API request, automatically setting error on
     * failure, else running the [onSuccess] callback if successful
     *
     * @param T Expected API call result data type
     * @param onRequest A block to run the actual API call
     * @param onError A block to run on failure
     * @param onSuccess A block to handle success
     * @param retryLimit Optional number of times to retry
     * @param retryIntervalMillis Optional delay between retries (requires [retryLimit] to be > 0)
     * @param isRetry Flag to improve some UX, like not clearing the error state between retries
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun <T : Any> networkRequest(
        requestId: Long = Instant.now().toEpochMilli(),
        onRequest: suspend () -> Result<T>,
        onError: (t: Throwable) -> Unit,
        onSuccess: (data: T?) -> Unit,
        retryLimit: Int? = null,
        retryIntervalMillis: Long? = null,
        isRetry: Boolean = false,
        backOff: Boolean = true,
        backOffFactor: Long = 2
    ) {
        
        plank("networkRequest ID# $requestId")
        
        viewModelScope.launch(Dispatchers.IO) {
            
            if (!isRetry) error.postValue(null)
            loading.postValue(true)
            
            // @todo refactor this to use a while instead of recursion
            
            val result = onRequest()
            
            if (result is Result.Error) {
                
                result.exception.printStackTrace()
                error.postValue(filterError(result.exception).message)
                onError(result.exception)
                
                if (retryLimit != null) {
                    
                    if (retryLimit <= 0) return@launch
                    
                    loading.postValue(false)
                    
                    val delayMillis = retryIntervalMillis ?: 5000
                    var newDelayMillis = delayMillis
                    
                    // If using the doubling backoff strategy, lengthen the delay
                    if (backOff) newDelayMillis *= minOf(delayMillis * backOffFactor, 60_000)
                    
                    delay(delayMillis)
                    
                    plank("networkRequest ID# $requestId (retry -${retryLimit})")
                    withContext(Dispatchers.Main) {
                        networkRequest(
                            requestId,
                            onRequest,
                            onError,
                            onSuccess,
                            (retryLimit - 1),
                            newDelayMillis,
                            true,
                            backOff,
                            backOffFactor
                        )
                    }
                    
                }
                
                return@launch
                
            }
            
            error.postValue(null)
            loading.postValue(false)
            onSuccess((result as Result.Success<T>).data)
            
        }
        
    }
    
    /**
     * Makes a network/API request, automatically setting error on
     * failure, else running the [onSuccess] callback if successful
     *
     * @param T Expected API call result data type
     * @param onRequest A block to run the actual API call
     * @param onSuccess A block to handle success
     * @param retryLimit Optional number of times to retry
     * @param retryIntervalMillis Optional delay between retries (requires [retryLimit] to be > 0)
     */
    fun <T : Any> networkRequest(
        onRequest: suspend () -> Result<T>,
        onSuccess: (data: T?) -> Unit,
        retryLimit: Int? = null,
        retryIntervalMillis: Long? = null
    ) = networkRequest(
        requestId = Instant.now().toEpochMilli(),
        onRequest = onRequest,
        onError = {},
        onSuccess = onSuccess,
        retryLimit = retryLimit,
        retryIntervalMillis = retryIntervalMillis
    )
    
    /**
     * Makes a network/API request, automatically setting error on
     * failure, else running the [onSuccess] callback if successful
     *
     * @param T Expected API call result data type
     * @param onRequest A block to run the actual API call
     * @param onSuccess A block to handle success
     * @param retryLimit Optional number of times to retry
     * @param retryIntervalMillis Optional delay between retries (requires [retryLimit] to be > 0)
     */
    fun <T : Any> networkRequest(
        onRequest: suspend () -> Result<T>,
        retryLimit: Int? = null,
        retryIntervalMillis: Long? = null
    ) = networkRequest(
        requestId = Instant.now().toEpochMilli(),
        onRequest = onRequest,
        onError = {},
        onSuccess = {},
        retryLimit = retryLimit,
        retryIntervalMillis = retryIntervalMillis
    )
    
}
