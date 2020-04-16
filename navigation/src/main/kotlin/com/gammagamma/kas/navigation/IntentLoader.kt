package com.gammagamma.kas.navigation

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import com.gammagamma.kas.logging.plank
import com.gammagamma.kas.logging.plankE
import org.koin.core.KoinComponent
import org.koin.core.inject

// https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture

object PackageProvider : KoinComponent {
    private val context: Context by inject()
    val packageName get() =
        context.applicationContext.packageName.replace(".dev", "")
}

/**
 * Creates a new intent based on a fully qualified class namespace
 *
 * [className] e.g. "com.foo.bar.MainActivity"
 */
private fun intentTo(className: String) : Intent =
    Intent(Intent.ACTION_VIEW)
        .setClassName(/*PACKAGE_NAME*/PackageProvider.packageName, className).apply {
            plank("$PACKAGE_NAME - $className")
        }

/**
 * Helper method to create an [Intent] from a fully qualified class namespace string
 *
 * @see [intentTo]
 */
internal fun String.loadIntentOrNull() : Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        plankE(e)
        null
    }

