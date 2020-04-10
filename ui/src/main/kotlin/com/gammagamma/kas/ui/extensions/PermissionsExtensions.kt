package com.gammagamma.kas.ui.extensions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

fun Activity.hasPermission(permission: String) : Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Fragment.hasPermission(permission: String) : Boolean =
    activity?.hasPermission(permission) ?: false

fun Activity.hasPermissions(permissions: Array<String>): Boolean {
    var has = true
    permissions.forEach { if (!hasPermission(it)) has = false }
    return has
}

fun Fragment.hasPermissions(permissions: Array<String>): Boolean =
    activity?.hasPermissions(permissions) ?: false

fun Activity.requestPermission(permission: String, code: Int) =
    ActivityCompat.requestPermissions(this, arrayOf(permission), code)

fun Fragment.requestPermission(permission: String, code: Int) =
    activity?.requestPermission(permission, code)

fun Activity.requestPermissionsCompat(permissions: Array<String>, code: Int) =
    ActivityCompat.requestPermissions(this, permissions, code)

fun Fragment.requestPermissionsCompat(permissions: Array<String>, code: Int) =
    ActivityCompat.requestPermissions(activity!!, permissions, code)

fun Activity.requirePermissions(permissions: Array<String>, code: Int): Boolean {
    if (hasPermissions(permissions)) return true
    requestPermissionsCompat(permissions, code)
    return false
}

fun Fragment.requirePermissions(permissions: Array<String>, code: Int): Boolean =
    activity?.requirePermissions(permissions, code) ?: false
