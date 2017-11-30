package com.kotlinandroidstarter.app.di

import android.support.v7.app.AppCompatActivity
import com.kotlinandroidstarter.app.activities.MainActivity
import com.kotlinandroidstarter.app.di.scopes.PerActivity
import com.kotlinandroidstarter.app.fragments.AFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [BaseActivityModule::class]/*,
    subcomponents = [AFragmentComponent::class]*/
)
abstract class MainActivityModule {
    
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
    
    /**
     * Provides the injector for the [AFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    /*@PerFragment
    @ContributesAndroidInjector(modules = [(AFragmentModule::class)])
    internal abstract fun aFragmentInjector(): AFragment*/
    
    /**
     * As per the contract specified in [BaseActivityModule]; "This must be included in all
     * activity modules, which must provide a concrete implementation of [AppCompatActivity]."
     *
     *
     * This provides the activity required to inject the
     * [BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER] into the
     * [com.kotlinandroidstarter.app.activities.BaseActivity].
     *
     * @param mainActivity the main activity
     * @return the activity
     */
    @Binds
    @PerActivity
    internal abstract fun appCompatActivity(mainActivity: MainActivity): AppCompatActivity
    
}