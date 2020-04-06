package com.kotlinandroidstarter.app.shared.ui

import androidx.lifecycle.MediatorLiveData
import com.kotlinandroidstarter.app.extensions.mediatorLiveDataOf
import com.kotlinandroidstarter.app.shared.net.ConnectivityLiveData

interface IViewModelEvents

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
    
    open var error = mediatorLiveDataOf<String>(null) {}
    open var loading = mediatorLiveDataOf(false) {}
    
    open val offline = mediatorLiveDataOf(false) {}
    
    open val hasError = MediatorLiveData<Boolean>().apply {
        addSource(error) {
            postValue(!it.isNullOrBlank())
        }
        value = false
    }
    
    // This needs to be lazy b/c the other mutables are initially null
    open val ready: MediatorLiveData<Boolean> by lazy {
        MediatorLiveData<Boolean>().apply {
            addSource(loading) { postValue(checkIfReady()) }
            addSource(error) { postValue(checkIfReady()) }
            addSource(offline) { postValue(checkIfReady()) }
            value = false
        }
    }
    
    init {
        
        this.networkAware = networkAware ?: false
        
    }
    
    private fun checkIfReady() : Boolean =
        try {
            loading.value == false
                && error.value.isNullOrBlank()
                && offline.value == false
        } catch (e: Exception) { false }
    
}
