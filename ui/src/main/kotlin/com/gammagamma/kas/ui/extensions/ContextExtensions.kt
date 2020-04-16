package com.gammagamma.kas.ui.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

inline fun <reified T: Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent =
    Intent(this, T::class.java).addExtras(*params)

fun Bundle?.addExtras(vararg extras: Pair<String, Any?>?) : Bundle? {
    val intent = Intent().addExtras(*extras)
    return intent.extras
}

fun Intent.addExtras(vararg extras: Pair<String, Any?>?): Intent =
    apply {
        extras.forEach {
            if (it != null)
                when (val value = it.second) {
                    null -> putExtra(it.first, null as Serializable?)
                    is Int -> putExtra(it.first, value)
                    is Long -> putExtra(it.first, value)
                    is CharSequence -> putExtra(it.first, value)
                    is String -> putExtra(it.first, value)
                    is Float -> putExtra(it.first, value)
                    is Double -> putExtra(it.first, value)
                    is Char -> putExtra(it.first, value)
                    is Short -> putExtra(it.first, value)
                    is Boolean -> putExtra(it.first, value)
                    is Serializable -> putExtra(it.first, value)
                    is Bundle -> putExtra(it.first, value)
                    is Parcelable -> putExtra(it.first, value)
                    is Array<*> -> when {
                        value.isArrayOf<CharSequence>() -> putExtra(it.first, value)
                        value.isArrayOf<String>() -> putExtra(it.first, value)
                        value.isArrayOf<Parcelable>() -> putExtra(it.first, value)
                        else -> throw RuntimeException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                    }
                    is IntArray -> putExtra(it.first, value)
                    is LongArray -> putExtra(it.first, value)
                    is FloatArray -> putExtra(it.first, value)
                    is DoubleArray -> putExtra(it.first, value)
                    is CharArray -> putExtra(it.first, value)
                    is ShortArray -> putExtra(it.first, value)
                    is BooleanArray -> putExtra(it.first, value)
                    else -> throw RuntimeException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }
        }
    }

fun Context.pxFromDp(dp: Float) : Int =
    (dp * resources.displayMetrics.density).toInt()
