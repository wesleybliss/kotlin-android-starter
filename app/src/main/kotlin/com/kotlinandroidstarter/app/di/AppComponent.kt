package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.App
import dagger.Component
import javax.inject.Singleton

/**
 * Injects application dependencies.
 */
@Singleton
@Component(modules = [AppModule::class, ApiServiceModule::class])
internal interface AppComponent {
    
    fun inject(app: App)
    
}