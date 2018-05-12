package com.kotlinandroidstarter.app.di

import android.app.Application
import com.kotlinandroidstarter.app.AppLifecycleCallbacks
import timber.log.Timber

class DebugAppLifecycleCallbacks : AppLifecycleCallbacks {
    
    override fun onCreate(application: Application) {
        Timber.plant(Timber.DebugTree())
    }
    
    override fun onTerminate(application: Application) = Unit
    
}
