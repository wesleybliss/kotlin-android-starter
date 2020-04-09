package com.gammagamma.kotlinandroidstarter.domain.net

sealed class Result<out T> {
    
    data class Error(val exception: Exception) : Result<Nothing>()
    
    data class Success<out T : Any>(val data: T) : Result<T>()
    
}
