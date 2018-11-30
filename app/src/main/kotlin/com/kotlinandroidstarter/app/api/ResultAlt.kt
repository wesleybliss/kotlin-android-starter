package com.kotlinandroidstarter.app.api

import okhttp3.Response
import retrofit2.HttpException

/**
 * Sealed class of HTTP result
 */

sealed class ResultAlt<out T : Any> {
    
    /**
     * Successful result of request without errors
     */
    class Ok<out T : Any>(
        val value: T,
        override val response: Response)
        : ResultAlt<T>(), ResponseResult {
        override fun toString() = "ResultAlt.Ok{value=$value, response=$response}"
    }
    
    /**
     * HTTP error
     */
    class Error(
        override val exception: HttpException,
        override val response: Response)
        : ResultAlt<Nothing>(), ErrorResult, ResponseResult {
        override fun toString() = "ResultAlt.Error{exception=$exception}"
    }
    
    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    class Exception(
        override val exception: Throwable)
        : ResultAlt<Nothing>(), ErrorResult {
        override fun toString() = "ResultAlt.Exception{$exception}"
    }
    
}

/**
 * Interface for [ResultAlt] classes with [okhttp3.Response]: [ResultAlt.Ok] and [ResultAlt.Error]
 */
interface ResponseResult {
    val response: Response
}

/**
 * Interface for [ResultAlt] classes that contains [Throwable]: [ResultAlt.Error] and [ResultAlt.Exception]
 */
interface ErrorResult {
    val exception: Throwable
}

/**
 * Returns [ResultAlt.Ok.value] or `null`
 */
fun <T : Any> ResultAlt<T>.getOrNull(): T? =
    (this as? ResultAlt.Ok)?.value

/**
 * Returns [ResultAlt.Ok.value] or [default]
 */
fun <T : Any> ResultAlt<T>.getOrDefault(default: T): T =
    getOrNull() ?: default

/**
 * Returns [ResultAlt.Ok.value] or throw [throwable] or [ErrorResult.exception]
 */
fun <T : Any> ResultAlt<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is ResultAlt.Ok -> value
        is ResultAlt.Error -> throw throwable ?: exception
        is ResultAlt.Exception -> throw throwable ?: exception
    }
}
