package com.gammagamma.kas.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    
    // @todo https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences
    
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        
        setPreferencesFromResource(R.xml.pref_main, rootKey)
        
    }
    
}
