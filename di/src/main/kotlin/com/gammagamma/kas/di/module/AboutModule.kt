package com.gammagamma.kas.di.module

import com.gammagamma.kas.about.AboutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AboutModule = module {
    
    viewModel { AboutViewModel() }
    
}
