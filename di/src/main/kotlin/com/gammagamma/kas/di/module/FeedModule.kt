package com.gammagamma.kas.di.module

import com.gammagamma.kas.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FeedModule = module {
    
    viewModel { FeedViewModel() }
    
}
