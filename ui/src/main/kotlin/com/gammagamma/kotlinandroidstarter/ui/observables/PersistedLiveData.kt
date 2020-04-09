package com.gammagamma.kotlinandroidstarter.ui.observables

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.MutableLiveData
import com.gammagamma.kotlinandroidstarter.domain.storage.IStorageProvider
import com.gammagamma.logging.plank
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject

/**
 * A [androidx.lifecycle.LiveData] implementation that reads and persists
 * it's value using [SharedPreferences] via [com.orhanobut.hawk.Hawk]
 */
open class PersistedLiveData<T>(
    @Suppress("MemberVisibilityCanBePrivate")
    val key: String,
    private val defaultValue: T? = null
) : KoinComponent, MutableLiveData<T>(), SharedPreferences.OnSharedPreferenceChangeListener {
    
    companion object {
    
        private val sharedPreferences by lazy {
            val context = (object : KoinComponent {}).get<Context>()
            PreferenceManager.getDefaultSharedPreferences(context)
        }
        
    }
    
    private val storage: IStorageProvider by inject()
    
    override fun onActive() {
        
        super.onActive()
        
        restoreValue()
        
        plank("onActive, read $key existing value: $value")
        
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        
    }
    
    override fun onInactive() {
        
        super.onInactive()
        
        plank("onInactive, writing $key value: $value")
        persistValue()
    
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        
    }
    
    override fun setValue(value: T?) {
        if (value == null) return
        super.setValue(value)
        persistValue()
    }
    
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        
        persistValue()
        
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun restoreValue() {
        value = storage.get(key, defaultValue)
        plank("#persistedLiveData value restored, $key, $value")
    }
    
    @Suppress("MemberVisibilityCanBePrivate")
    fun persistValue() {
        storage.put(key, value)
        plank("#persistedLiveData value saved, $key, $value")
    }
    
}

inline fun <reified T> persistedLiveDataOf(key: String, defaultValue: T? = null) =
    PersistedLiveData(key, defaultValue)
