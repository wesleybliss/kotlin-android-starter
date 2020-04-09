package com.gammagamma.di.modules

import com.gammagamma.kotlinandroidstarter.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeedModule = module {
    
    viewModel { FeedViewModel() }
    
}
