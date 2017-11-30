package com.kotlinandroidstarter.app.di

import dagger.Module

@Module
abstract class BaseFragmentModule {
    
    companion object {
    
        /**
         * See [BaseChildFragmentModule] class documentation regarding the need for this name.
         */
        const val FRAGMENT = "BaseFragmentModule.fragment"
        
    }
    
    val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager"
    
    /*@Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    fun childFragmentManager(@Named(FRAGMENT) fragment: Fragment): FragmentManager {
        return fragment.getChildFragmentManager()
    }*/
    
}