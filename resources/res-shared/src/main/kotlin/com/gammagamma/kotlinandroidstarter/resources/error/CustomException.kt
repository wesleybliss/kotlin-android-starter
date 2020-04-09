package com.gammagamma.kotlinandroidstarter.resources.error

import android.content.Context
import androidx.annotation.StringRes
import org.koin.core.KoinComponent
import org.koin.core.get

open class CustomException(@StringRes messageId: Int)
    : RuntimeException((object : KoinComponent {}).get<Context>().getString(messageId))
