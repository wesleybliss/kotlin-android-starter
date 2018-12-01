package com.kotlinandroidstarter.app.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

/**
 * Base, native, Android Architecture Components ViewModel,
 * useful to extend for any ViewModel that needs error & loading states
 */
open class BaseViewModel : ScopedViewModel() {

    open val error = MutableLiveData<String>().apply { value = null }
    open val loading = MutableLiveData<Boolean>().apply { value = false }

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
            value = false
        }
    }

    private fun checkIfReady() : Boolean =
        try { loading.value != true && error.value.isNullOrBlank() }
        catch (e: Exception) { false }

    /*
    These are marked open so they can be modified if needed.
    An example of `loading` depending on multiple things might look like this:
    
    val loadingThing1 = MutableLiveData<Boolean>().apply { value = false }
    val loadingThing2 = MutableLiveData<Boolean>().apply { value = false }
    
    override val loading = MediatorLiveData<Boolean>().apply {
        addSource(loadingThing1) {
            if (it == true && loadingThing2.value == true)
                postValue(true)
        }
        addSource(loadingThing2) {
            if (it == true && loadingThing1.value == true)
                postValue(true)
        }
    }
    */

}
