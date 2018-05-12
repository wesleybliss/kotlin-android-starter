package com.kotlinandroidstarter.app.di.modules

import com.kotlinandroidstarter.app.AppLifecycleCallbacks
import com.kotlinandroidstarter.app.di.DebugAppLifecycleCallbacks
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
internal object AppModule {
    
    @Singleton
    @Provides
    @JvmStatic
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()
    
    /*
    Example of injectable arbitrary data:
    
    @Provides
    @Singleton
    @Named("something")
    fun provideSomething(): String = "something"

    @Provides
    @Singleton
    @Named("somethingElse")
    fun provideSomethingElse(): String = "somethingElse"
    */
    
}
