package com.gammagamma.kas.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gammagamma.kas.navigation.Navigation.launchMainActivity
import com.gammagamma.kas.navigation.Navigation.launchSettingsActivity

class SplashActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        launchMainActivity()
        finish()
        
    }
    
}
