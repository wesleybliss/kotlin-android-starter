package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Injects application dependencies.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    UiModule::class
])
interface AppComponent : AndroidInjector<App> {
    
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        fun application(application: App): Builder
        
        fun build(): AppComponent
        
    }
    
    override fun inject(app: App)
    
}
