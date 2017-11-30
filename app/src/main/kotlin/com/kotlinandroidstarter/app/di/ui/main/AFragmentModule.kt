package com.kotlinandroidstarter.app.di.ui.main

import android.support.v4.app.Fragment
import com.kotlinandroidstarter.app.di.scopes.PerFragment
import com.kotlinandroidstarter.app.di.ui.shared.BaseFragmentModule
import com.kotlinandroidstarter.app.fragments.AFragment
import dagger.Binds
import dagger.Module
import javax.inject.Named

/**
 * Provides main fragment dependencies.
 */
@Module(includes = [BaseFragmentModule::class])
abstract class AFragmentModule {
    
    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment]
     * and named [BaseFragmentModule.FRAGMENT].
     *
     * @param aFragment the main fragment
     * @return the fragment
     */
    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    internal abstract fun fragment(aFragment: AFragment): Fragment
    
}