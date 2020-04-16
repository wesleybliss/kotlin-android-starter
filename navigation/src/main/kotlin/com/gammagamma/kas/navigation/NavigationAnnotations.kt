package com.gammagamma.kas.navigation

import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(CONSTRUCTOR, FUNCTION)
@MustBeDocumented
annotation class PreferMethod(
    val message: String,
    val preferredMethod: String
)
