package com.kotlinandroidstarter.app.di.ui.shared

import dagger.Module

@Module
abstract class BaseFragmentModule {
    
    companion object {
        
        /**
         * See [BaseChildFragmentModule] class documentation regarding the need for this name.
         */
        const val FRAGMENT = "BaseFragmentModule.fragment"
        
    }
    
}