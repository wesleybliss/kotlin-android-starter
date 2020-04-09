package com.gammagamma.kotlinandroidstarter.ui.extensions

import android.content.Context
import android.widget.Toast
import org.koin.core.KoinComponent
import org.koin.core.get

fun toastGlobal(message: String) =
    (object : KoinComponent {}).get<Context>()/*.applicationContext*/.apply {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
