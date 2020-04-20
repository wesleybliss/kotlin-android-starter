package com.gammagamma.kas.domain.net

sealed class Result<out T> {
    
    data class Error(val exception: Exception) : Result<Nothing>()
    
    data class Success<out T>(val data: T?) : Result<T>()
    
}
