package com.kotlinandroidstarter.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.kotlinandroidstarter.app.di.AppComponent
import com.kotlinandroidstarter.app.di.DaggerAppComponent
import com.kotlinandroidstarter.app.di.applyAutoInjector
import com.kotlinandroidstarter.app.utils.CrashReportingTree
import com.orhanobut.hawk.Hawk
import dagger.android.support.DaggerApplication
import javax.inject.Inject
import timber.log.Timber
import timber.log.Timber.DebugTree



class App : DaggerApplication() {
    
    /*companion object {
    
        @JvmStatic lateinit var instance: AppComponent
            private set
        
    }*/
    
    @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks
    
    /*@Inject
    * lateinit var locationManager: LocationManager*/

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()


    override fun onCreate() {
        
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        else Timber.plant(CrashReportingTree())
        
        // Shared preferences helper
        Hawk.init(this).build()
        
        applyAutoInjector()
        appLifecycleCallbacks.onCreate(this)
        
        /*registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                p0?.let { AndroidInjection.inject(p0) }
            }
        })*/
        
        // Realm.init(this)
        // Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
        
    }

    override fun onTerminate() {
        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }

    abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(p0: Activity?) {}
        override fun onActivityResumed(p0: Activity?) {}
        override fun onActivityStarted(p0: Activity?) {}
        override fun onActivityDestroyed(p0: Activity?) {}
        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}
        override fun onActivityStopped(p0: Activity?) {}
        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {}
    }
    
}
