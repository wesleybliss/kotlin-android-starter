package com.kotlinandroidstarter.app.di

import android.support.v4.app.Fragment
import com.kotlinandroidstarter.app.di.scopes.PerFragment
import com.kotlinandroidstarter.app.fragments.AFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module(includes = [
    (BaseFragmentModule::class),
    (ApiRepoModule::class)
])
abstract class AFragmentModule {
    
    @ContributesAndroidInjector
    internal abstract fun contributeAFragment(): AFragment
    
    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment]
     * and named [BaseFragmentModule.FRAGMENT].
     *
     * @param example1Fragment the example 1 fragment
     * @return the fragment
     */
    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    internal abstract fun fragment(aFragment: AFragment): Fragment
    
    /*@Binds
    @PerFragment
    internal abstract fun example1View(example1Fragment: AFragment): Example1View*/
    
}