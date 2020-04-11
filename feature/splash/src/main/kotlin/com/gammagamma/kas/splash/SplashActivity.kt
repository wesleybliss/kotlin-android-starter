package com.gammagamma.kas.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gammagamma.kas.main.MainActivity
import splitties.activities.start

class SplashActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        
        start<MainActivity>()
        finish()
        
    }
    
}
