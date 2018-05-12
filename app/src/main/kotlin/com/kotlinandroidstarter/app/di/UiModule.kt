package com.kotlinandroidstarter.app.di

import android.arch.lifecycle.ViewModelProvider
import com.kotlinandroidstarter.app.activities.MainActivity
import com.kotlinandroidstarter.app.di.module.MainModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}
