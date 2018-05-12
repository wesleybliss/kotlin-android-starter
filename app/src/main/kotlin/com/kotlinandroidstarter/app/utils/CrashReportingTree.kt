package com.kotlinandroidstarter.app.utils

import android.support.annotation.NonNull
import android.util.Log
import timber.log.Timber


/** A tree which logs important information for crash reporting.  */
internal class CrashReportingTree : Timber.Tree() {
    
    override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
        
        if (priority == Log.VERBOSE || priority == Log.DEBUG)
            return
        
        FakeCrashLibrary.log(priority, tag ?: "UNKNOWN", message)
        
        if (t != null)
            if (priority == Log.ERROR)
                FakeCrashLibrary.logError(t)
            else if (priority == Log.WARN)
                FakeCrashLibrary.logWarning(t)
        
    }
    
}
