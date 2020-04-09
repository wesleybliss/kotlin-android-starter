package com.gammagamma.kotlinandroidstarter.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("unused")
class ConnectivityLiveData : LiveData<Boolean>(), KoinComponent {
    
    companion object {
        
        val instance by lazy { ConnectivityLiveData() }
        
        val isOnline: Boolean get() = (instance.value == true)
        
        inline fun observe(
            owner: LifecycleOwner,
            crossinline fn: (Boolean) -> Unit) : Observer<Boolean> {
            val observer = Observer<Boolean> { fn(it) }
            instance.observe(owner, observer)
            return observer
        }
        
        inline fun observeOnce(
            owner: LifecycleOwner,
            crossinline fn: (Boolean) -> Unit) : Observer<Boolean> {
            val observer = object : Observer<Boolean> {
                override fun onChanged(it: Boolean?) {
                    if (it != null) {
                        fn(it)
                        instance.removeObserver(this)
                    }
                }
            }
            instance.observe(owner, observer)
            return observer
        }
        
    }
    
    private val applicationContext: Context by inject()
    
    fun forceValue(newValue: Boolean) {
        value = newValue
    }
    
    private val connectivityManager: ConnectivityManager by lazy {
        applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        
        // Try/catch on these is bc sometimes (e.g. during tests), this runs
        // on the main thread, and others it runs on a background thread
        
        override fun onAvailable(network: Network?) {
            try { value = true }
            catch (e: Exception) { postValue(true) }
        }
        
        override fun onLost(network: Network?) {
            try { value = false }
            catch (e: Exception) { postValue(false) }
        }
        
    }
    
    override fun onActive() {
        
        super.onActive()
        
        val activeNetwork = connectivityManager.activeNetworkInfo
        
        postValue(activeNetwork?.isConnected == true)
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
        
    }
    
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
    
}
