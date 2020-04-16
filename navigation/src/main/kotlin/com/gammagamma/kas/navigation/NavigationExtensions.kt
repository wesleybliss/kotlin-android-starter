package com.gammagamma.kas.navigation

import android.content.Intent

@Suppress("NOTHING_TO_INLINE")
inline fun Intent.clearTask(): Intent =
    apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }

@Suppress("NOTHING_TO_INLINE")
inline fun Intent.newTask(): Intent =
    apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
