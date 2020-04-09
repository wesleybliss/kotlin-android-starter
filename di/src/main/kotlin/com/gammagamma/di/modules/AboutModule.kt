package com.gammagamma.di.modules

import com.gammagamma.kotlinandroidstarter.about.AboutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AboutModule = module {
    
    viewModel { AboutViewModel() }
    
}
