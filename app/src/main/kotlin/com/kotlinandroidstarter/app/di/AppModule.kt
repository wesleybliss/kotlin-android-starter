package com.kotlinandroidstarter.app.di

import com.kotlinandroidstarter.app.features.about.AboutViewModel
import com.kotlinandroidstarter.app.features.feed.FeedViewModel
import com.kotlinandroidstarter.app.features.home.HomeViewModel
import com.kotlinandroidstarter.app.repositories.UsersRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {}

val viewModule = module {
    
    //viewModel { SharedViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { FeedViewModel() }
    viewModel { AboutViewModel() }
    
}

val repositoryModule = module {
    
    single { UsersRepository(get()) }
    
}
