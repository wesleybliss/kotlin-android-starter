package com.kotlinandroidstarter.app.errors

class DataEmptyOrInvalidException(
    val eventName: String
) : RuntimeException()