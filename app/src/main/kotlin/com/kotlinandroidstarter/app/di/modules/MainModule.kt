package com.kotlinandroidstarter.app.di.modules

import android.arch.lifecycle.ViewModel
import com.kotlinandroidstarter.app.di.ViewModelKey
import com.kotlinandroidstarter.app.viewmodels.PostsViewModel
import com.kotlinandroidstarter.app.viewmodels.UsersViewModel
import com.kotlinandroidstarter.app.fragments.AFragment
import com.kotlinandroidstarter.app.fragments.BFragment
import com.kotlinandroidstarter.app.fragments.CFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class MainModule {
    
    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    abstract fun bindUsersViewModel(viewModel: UsersViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel
    
    /*@ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment*/
    
    @ContributesAndroidInjector
    abstract fun contributeAFragment(): AFragment
    
    @ContributesAndroidInjector
    abstract fun contributeBFragment(): BFragment
    
    @ContributesAndroidInjector
    abstract fun contributeCFragment(): CFragment
    
}   
