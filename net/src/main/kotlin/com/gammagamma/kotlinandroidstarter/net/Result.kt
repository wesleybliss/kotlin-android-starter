package com.gammagamma.kotlinandroidstarter.net

sealed class Result<out T : Any> {
    
    data class Error(val exception: Exception) : Result<Nothing>()
    
    data class Success<out T : Any>(val data: T) : Result<T>()
    
}
