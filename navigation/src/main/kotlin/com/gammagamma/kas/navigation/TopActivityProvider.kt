package com.gammagamma.kas.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.gammagamma.kas.logging.plankE
import javax.inject.Provider

class TopActivityProvider : Provider<Activity> {
    
    var topActivity: Activity? = null
    
    val callbacks = object : Application.ActivityLifecycleCallbacks {
        
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            topActivity = activity
        }
        
        override fun onActivityStarted(activity: Activity) {}
        
        override fun onActivityResumed(activity: Activity) {
            topActivity = activity
        }
        
        override fun onActivityPaused(activity: Activity) {}
        
        override fun onActivityStopped(activity: Activity) {}
        
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}
        
        override fun onActivityDestroyed(activity: Activity) {}
        
    }
    
    
    override fun get() =
        topActivity ?: "No activity".let {
            plankE(it)
            throw IllegalStateException(it)
        }
    
}
