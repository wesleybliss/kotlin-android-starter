package com.gammagamma.kotlinandroidstarter.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.gammagamma.kotlinandroidstarter.domain.net.Result
import com.gammagamma.kotlinandroidstarter.resources.error.filterError
import com.gammagamma.kotlinandroidstarter.ui.extensions.addSources
import com.gammagamma.kotlinandroidstarter.ui.extensions.mediatorLiveDataOf
import com.gammagamma.logging.plank
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.Instant

/**
 * Base, native, Android Architecture Components ViewModel,
 * useful to extend for any ViewModel that needs error & loading states
 */
abstract class StatefulBaseViewModel(networkAware: Boolean? = false) : BaseViewModel() {
    
    open var networkAware = false
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
    
    init {
        
        this.networkAware = networkAware ?: false
        
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
        onSuccess: (data: T) -> Unit,
        retryLimit: Int? = null,
        retryIntervalMillis: Long? = null,
        isRetry: Boolean = false
    ) {
        
        plank("networkRequest ID# $requestId")
        
        viewModelScope.launch(Dispatchers.IO) {
            
            if (!isRetry) error.postValue(null)
            loading.postValue(true)
    
            val result = onRequest()
            
            if (result is Result.Error) {
                
                error.postValue(filterError(result.exception).message)
                onError(result.exception)
                
                if (retryLimit != null) {
                    
                    if (retryLimit <= 0) return@launch
                    
                    loading.postValue(false)
                    
                    val delayMillis = retryIntervalMillis ?: 5000
                    
                    delay(delayMillis)
                    
                    plank("networkRequest ID# $requestId (retry -${retryLimit})")
                    withContext(Dispatchers.Main) {
                        networkRequest(
                            requestId,
                            onRequest,
                            onError,
                            onSuccess,
                            (retryLimit - 1),
                            delayMillis,
                            true
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
        onSuccess: (data: T) -> Unit,
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
    
}
