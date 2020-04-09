package com.gammagamma.kotlinandroidstarter.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gammagamma.kotlinandroidstarter.domain.ui.IViewModelEvents
import com.gammagamma.kotlinandroidstarter.ui.extensions.mutableLiveDataOf

abstract class BaseViewModel : ViewModel() {
    
    open val eventDispatcher = mutableLiveDataOf<IViewModelEvents>(null)
    
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
    
    fun <T : IViewModelEvents> dispatchEvent(event: T) {
        eventDispatcher.value = event
    }
    
    /**
     * Helper to observe event state
     *
     * @param activity
     * @param handleEvent Callback to handle event
     */
    inline fun <reified T : IViewModelEvents> observeEventDispatcher(
        activity: LifecycleOwner,
        crossinline handleEvent: (event: T) -> Unit) {
        eventDispatcher.observe(activity, Observer {
            if (it !== null) {
                handleEvent(it as T)
                eventDispatcher.value = null
            }
        })
    }
    
    inline fun <reified T : IViewModelEvents> observeEventDispatcher(
        fragment: Fragment,
        crossinline handleEvent: (event: T) -> Unit) {
        eventDispatcher.observe(fragment, Observer {
            if (it !== null) {
                handleEvent(it as T)
                eventDispatcher.value = null
            }
        })
    }
    
}
