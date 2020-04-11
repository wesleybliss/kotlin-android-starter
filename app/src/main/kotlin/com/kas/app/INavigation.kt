package com.kas.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle

interface INavigation {
    
    fun <C: Activity, T: Activity> startSplashActivity(caller: C, bundle: Intent? = null, options: Bundle? = null)
    fun <C: Activity, T: Activity> startMainActivity(caller: C, bundle: Intent? = null, options: Bundle? = null)
    fun <C: Activity, T: Activity> startSettingsActivity(caller: C, bundle: Intent? = null, options: Bundle? = null)
    
}
