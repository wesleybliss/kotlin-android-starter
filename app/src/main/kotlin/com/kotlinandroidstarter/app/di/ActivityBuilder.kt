package com.kotlinandroidstarter.app.di

import android.app.Activity
import com.kotlinandroidstarter.app.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class ActivityBuilder {
    
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivity(builder: MainActivityComponent.Builder)
        : AndroidInjector.Factory<out Activity>
    
}
