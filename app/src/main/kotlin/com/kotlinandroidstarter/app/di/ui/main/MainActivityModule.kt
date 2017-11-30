package com.kotlinandroidstarter.app.di.ui.main

import android.app.Activity
import com.kotlinandroidstarter.app.activities.MainActivity
import com.kotlinandroidstarter.app.di.scopes.PerActivity
import com.kotlinandroidstarter.app.di.scopes.PerFragment
import com.kotlinandroidstarter.app.di.ui.shared.BaseActivityModule
import com.kotlinandroidstarter.app.fragments.AFragment
import com.kotlinandroidstarter.app.fragments.BFragment
import com.kotlinandroidstarter.app.fragments.CFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Provides main activity dependencies.
 */
@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {
    
    /**
     * Provides the injector for the [MainFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    
    @PerFragment
    @ContributesAndroidInjector(modules = [AFragmentModule::class])
    internal abstract fun aFragmentInjector(): AFragment
    
    @PerFragment
    @ContributesAndroidInjector(modules = [BFragmentModule::class])
    internal abstract fun bFragmentInjector(): BFragment
    
    @PerFragment
    @ContributesAndroidInjector(modules = [CFragmentModule::class])
    internal abstract fun cFragmentInjector(): CFragment
    
    /**
     * As per the contract specified in [BaseActivityModule]; "This must be included in all
     * activity modules, which must provide a concrete implementation of [Activity]."
     *
     *
     * This provides the activity required to inject the
     * [BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER] into the
     * [com.kotlinandroidstarter.app.activities.BaseActivity].
     *
     * @param mainActivity the activity
     * @return the activity
     */
    @Binds
    @PerActivity
    internal abstract fun activity(mainActivity: MainActivity): Activity
    
}