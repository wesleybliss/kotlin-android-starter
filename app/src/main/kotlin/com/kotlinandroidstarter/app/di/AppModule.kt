package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.activities.MainActivity
import com.kotlinandroidstarter.app.di.scopes.PerActivity
import com.kotlinandroidstarter.app.di.ui.main.MainActivityModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {
    
    /**
     * Provides the injector for the [MainActivity], which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun mainActivityInjector(): MainActivity
    
}