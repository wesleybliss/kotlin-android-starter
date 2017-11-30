package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.fragments.AFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainActivityModule::class])
interface AFragmentComponent : AndroidInjector<AFragment> {
    
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AFragment>()
    
}