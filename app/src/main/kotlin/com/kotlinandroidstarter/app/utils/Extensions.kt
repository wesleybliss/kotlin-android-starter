package com.kotlinandroidstarter.app.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


fun Activity.hasPermission(permission: String) : Boolean =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Fragment.hasPermission(permission: String) : Boolean =
    activity!!.hasPermission(permission)

infix fun Activity.hasPermissions(permissions: Array<String>): Boolean {
    var has = true
    permissions.forEach { if (!hasPermission(it)) has = false }
    return has
}

fun Fragment.hasPermissions(permissions: Array<String>): Boolean =
    activity!!hasPermissions(permissions)

fun Activity.requestPermission(permission: String, code: Int) =
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), code)

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
    activity!!.requirePermissions(permissions, code)

fun Activity.persistStorageAccess(treeUri: Uri, modeFlags: Int) =
    contentResolver.takePersistableUriPermission(treeUri, modeFlags)

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun Activity.simpleDialog(title: String, message: String, confirmed: () -> Unit) =
    AlertDialog.Builder(this).setTitle(title).setMessage(message)
        .setNegativeButton("Cancel", null)
        .setPositiveButton("OK", { _, _ -> confirmed() })!!

fun Activity.infoDialog(title: String, message: String, confirmed: () -> Unit) =
    AlertDialog.Builder(this).setTitle(title).setMessage(message)
        .setPositiveButton("OK", { _, _ -> confirmed() })!!

fun Fragment.simpleDialog(title: String, message: String, confirmed: () -> Unit) =
    activity!!.simpleDialog(title, message, confirmed)

fun Activity.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: String) =
    activity!!.toast(message)

// @todo Debug
fun Activity.checkStoragePermission(): Boolean {
    // Verify that all required contact permissions have been granted.
    return ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
}
