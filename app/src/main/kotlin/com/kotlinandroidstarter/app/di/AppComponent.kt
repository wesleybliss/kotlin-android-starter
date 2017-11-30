package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (AppModule::class),
    ActivityBuilder::class/*,
    (BaseActivityModule::class),
    (MainActivityModule::class)*//*,
    (AFragmentModule::class),
    (ApiRepoModule::class)*/
])
interface AppComponent {
    
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        fun app(app: App): Builder
        
        fun build(): AppComponent
        
    }
    
    fun inject(app: App)
    
}