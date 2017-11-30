package com.kotlinandroidstarter.app.di

import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [AndroidSupportInjectionModule::class],
    subcomponents = [MainActivityComponent::class, AFragmentComponent::class]
)
internal class AppModule {
    
    /*@Provides
    @Singleton
    fun provideContext(app: App): Context = app*/
    
    /*@Inject
    @Provides
    @Singleton
    fun provideApiRepo @Inject constructor(private apiService: ApiService): ApiRepo = ApiRepo(apiService)*/
    
}