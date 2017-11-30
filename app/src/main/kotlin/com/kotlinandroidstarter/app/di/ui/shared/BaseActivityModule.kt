package com.kotlinandroidstarter.app.di.ui.shared

import android.app.Activity
import android.content.Context
import com.kotlinandroidstarter.app.di.scopes.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {
    
    @Binds
    @PerActivity
    internal abstract fun activityContext(activity: Activity): Context
    
}