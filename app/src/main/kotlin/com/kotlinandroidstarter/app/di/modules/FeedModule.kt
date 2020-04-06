package com.kotlinandroidstarter.app.di.modules

import com.kotlinandroidstarter.app.features.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeedModule = module {
    
    viewModel { FeedViewModel() }
    
}
