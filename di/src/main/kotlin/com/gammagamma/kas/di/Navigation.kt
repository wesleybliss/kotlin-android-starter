package com.gammagamma.kas.di

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.gammagamma.kas.main.MainActivity
import com.gammagamma.kas.settings.SettingsActivity
import com.gammagamma.kas.splash.SplashActivity

object Navigation : INavigation {
    
    inline fun <C: Activity, reified T: Activity> startAnyActivity(
        caller: C,
        bundle: Intent? = null,
        options: Bundle? = null
    ) {
        val intent = Intent(caller, T::class.java)
        bundle?.also { intent.putExtras(it) }
        startActivity(caller, intent, options)
    }
    
    inline fun <C: Activity, reified T: Activity> startSplashActivity(
        caller: C, bundle: Intent? = null, options: Bundle? = null) =
        startAnyActivity<C, SplashActivity>(caller, bundle, options)
    
    inline fun <C: Activity, reified T: Activity> startMainActivity(
        caller: C, bundle: Intent? = null, options: Bundle? = null) =
        startAnyActivity<C, MainActivity>(caller, bundle, options)
    
    inline fun <C: Activity, reified T: Activity> startSettingsActivity(
        caller: C, bundle: Intent? = null, options: Bundle? = null) =
        startAnyActivity<C, SettingsActivity>(caller, bundle, options)
    
}
