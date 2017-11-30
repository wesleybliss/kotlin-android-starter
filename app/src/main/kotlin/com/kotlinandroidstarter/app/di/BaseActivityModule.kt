package com.kotlinandroidstarter.app.di

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.kotlinandroidstarter.app.di.scopes.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class BaseActivityModule {
    
    @Module
    companion object {
        
        const val ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager"
    
        @JvmStatic
        @Provides
        @Named(ACTIVITY_FRAGMENT_MANAGER)
        @PerActivity
        internal fun activityFragmentManager(activity: AppCompatActivity): FragmentManager {
            return activity.supportFragmentManager
        }
        
    }
    
    @Binds
    @PerActivity
    /*
     * PerActivity annotation isn't necessary since Activity instance is unique but is here for
     * convention. In general, providing Application, Activity, Fragment, BroadcastReceiver,
     * etc does not require scoped annotations since they are the components being injected and
     * their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    internal abstract fun activity(appCompatActivity: AppCompatActivity): Activity
    
    @Binds
    @PerActivity
    internal abstract fun activityContext(activity: Activity): Context
    
}