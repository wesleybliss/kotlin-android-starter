package com.kotlinandroidstarter.app.di

val koinModules = listOf(
    AppModule.module,
    ApiModule.module,
    ViewModule.module,
    RepositoryModule.module
    /*interactorsModule,
    mappersModule,
    networkModule*/
)
