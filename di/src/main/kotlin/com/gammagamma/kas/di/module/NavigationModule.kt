package com.gammagamma.kas.di.module

import android.app.Activity
import com.gammagamma.kas.navigation.TopActivityProvider
import org.koin.dsl.module
import javax.inject.Provider

val NavigationModule = module { 
    
    single<Provider<Activity>> { TopActivityProvider() }
    
}
