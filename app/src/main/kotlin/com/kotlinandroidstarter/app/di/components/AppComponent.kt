package com.kotlinandroidstarter.app.di.components

import com.kotlinandroidstarter.app.App
import com.kotlinandroidstarter.app.di.modules.AppModule
import com.kotlinandroidstarter.app.di.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

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
