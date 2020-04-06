package com.kotlinandroidstarter.app.di.modules

import com.kotlinandroidstarter.app.features.about.AboutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AboutModule = module {
    
    viewModel { AboutViewModel() }
    
}
