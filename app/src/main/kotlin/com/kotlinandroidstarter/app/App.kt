package com.kotlinandroidstarter.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.kotlinandroidstarter.app.di.DaggerAppComponent
import com.kotlinandroidstarter.app.repos.ApiRepo
import com.orhanobut.hawk.Hawk
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    
    companion object {
    
        @JvmStatic lateinit var instance: App
            private set
        
        @JvmStatic lateinit var apiRepo: ApiRepo
        
    }
    
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
    
    override fun onCreate() {
    
        super.onCreate()
    
        instance = this
        
        // Shared preferences helper
        Hawk.init(this).build()
        
        DaggerAppComponent.builder()
            .app(this)
            .build()
            .inject(this)
    
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                p0?.let { AndroidInjection.inject(p0) }
            }
        })
    
        // Realm.init(this)
        // Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
        
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