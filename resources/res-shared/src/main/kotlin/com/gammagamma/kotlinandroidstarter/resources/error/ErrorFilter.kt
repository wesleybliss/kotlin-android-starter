package com.gammagamma.kotlinandroidstarter.resources.error

import java.net.ConnectException

fun filterError(t: Throwable) = when (t) {
    is ConnectException -> ApiConnectionError()
    else -> t
}
