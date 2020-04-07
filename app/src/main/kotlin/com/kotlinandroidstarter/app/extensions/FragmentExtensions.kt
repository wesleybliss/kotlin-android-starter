package com.kotlinandroidstarter.app.extensions

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.kotlinandroidstarter.app.R
import com.gammagamma.logging.plankE

fun Fragment.toast(message: String) =
    activity?.toast(message)

//region Dialogs

fun Fragment.confirm(
    title: String,
    message: String,
    negativeText: String = getString(R.string.default_no),
    positiveText: String = getString(R.string.default_yes),
    onNegativeClicked: (() -> Unit)? = null,
    onPositiveClicked: () -> Unit
) = context?.let { ctx ->
    AlertDialog.Builder(ctx)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(negativeText) { _, _ -> onNegativeClicked?.invoke() }
        .setPositiveButton(positiveText) { _, _ -> onPositiveClicked() }
        .show()
} ?: plankE("Context was null").let { null }

fun Fragment.confirm(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes negativeText: Int = R.string.default_no,
    @StringRes positiveText: Int = R.string.default_yes,
    onNegativeClicked: (() -> Unit)? = null,
    onPositiveClicked: () -> Unit
) = confirm(
    getString(title),
    getString(message),
    getString(negativeText),
    getString(positiveText),
    onNegativeClicked,
    onPositiveClicked
)

//endregion Dialogs
