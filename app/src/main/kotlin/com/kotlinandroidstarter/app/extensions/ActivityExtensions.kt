package com.kotlinandroidstarter.app.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kotlinandroidstarter.app.R

fun Activity.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.setupActionBar(
    enableHome: Boolean,
    displayHomeAsUp: Boolean,
    title: String,
    titleSize: Float,
    @ColorRes titleColor: Int = android.R.color.black) {
    
    val textView = TextView(this)
    
    textView.text = title
    textView.textSize = titleSize
    //textView.typeface = CustomTypeface.loadTypeface(CustomTypeface.FontAsset.FOO_FONT_SEMIBOLD)
    //textView.gravity = Gravity.CENTER
    
    textView.layoutParams =
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
    
    val layoutParams = ActionBar.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.MATCH_PARENT,
        Gravity.CENTER_HORIZONTAL
    )
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        textView.setTextColor(resources.getColor(titleColor, theme))
    else
        @Suppress("DEPRECATION")
        textView.setTextColor(resources.getColor(titleColor))
    
    supportActionBar!!.setCustomView(textView, layoutParams)
    supportActionBar!!.setDisplayShowCustomEnabled(true)
    supportActionBar!!.setHomeButtonEnabled(enableHome)
    supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUp)
    
}

fun Activity.setStatusBarColor(@ColorInt color: Int) {
    
    // clear FLAG_TRANSLUCENT_STATUS flag:
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    
    // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    
    // finally change the color
    window.statusBarColor = color
    
}

fun Activity.setStatusBarColorRes(@ColorRes colorId: Int = android.R.color.transparent) {
    setStatusBarColor(ContextCompat.getColor(this, colorId))
}

fun Activity.setWindowFlag(bits: Int, on: Boolean) {
    val win = window
    val winParams = win.attributes
    if (on)
        winParams.flags = winParams.flags or bits
    else
        winParams.flags = winParams.flags and bits.inv()
    win.attributes = winParams
}

//region StatusBar

fun Activity.showBehindStatusBar(@ColorRes color: Int = android.R.color.transparent, includeNavBar: Boolean = false) {
    
    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
    
    val flags =
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    
    window.decorView.systemUiVisibility =
        if (!includeNavBar) flags
        else flags or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        @ColorInt
        val statusBarColor = ContextCompat.getColor(this, color)
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = statusBarColor
    }
    
}

fun Activity.setLightStatusBar(@ColorInt color: Int = Color.WHITE) {
    val view = window.decorView
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
        //window.statusBarColor = color
    }
    setStatusBarColor(color)
}

fun Activity.setLightStatusBarRes(@ColorRes color: Int) {
    setLightStatusBar(ContextCompat.getColor(this, color))
}

fun Activity.clearLightStatusBar(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.statusBarColor = ContextCompat.getColor(this, color)
}

fun Activity.makeStatusBarTransparent() {
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
    
}

//endregion StatusBar

//region NavigationBar

fun Activity.hideNavigationBar() {
    window.decorView.systemUiVisibility =
        SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            /*SYSTEM_UI_FLAG_LAYOUT_STABLE or*/
            /*SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or*/
            SYSTEM_UI_FLAG_FULLSCREEN or
            SYSTEM_UI_FLAG_IMMERSIVE
}

fun Activity.setLightNavigationBar(@ColorInt color: Int = Color.WHITE, setFlag: Boolean = true) {
    
    val view = window.decorView
    var flags = view.systemUiVisibility
    
    if (setFlag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        view.systemUiVisibility = flags
    }
    
    window.navigationBarColor =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) color
        else ContextCompat.getColor(this, android.R.color.black)
    
}

fun Activity.setLightNavigationBarRes(@ColorRes color: Int) {
    setLightNavigationBar(
        ContextCompat.getColor(this,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) color else android.R.color.black
        )
    )
}

fun Activity.clearLightNavigationBar(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.navigationBarColor =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ContextCompat.getColor(this, color)
            else ContextCompat.getColor(this, android.R.color.black)
}

@SuppressLint("ObsoleteSdkInt")
fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.black)
        return
    }
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.navigationBarColor = color
        return
    }
    
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }
    
}

fun Activity.setNavigationBarColorRes(@ColorRes color: Int) {
    setNavigationBarColor(ContextCompat.getColor(this, color))
}

//endregion NavigationBar

//region Dialogs

fun Activity.confirm(
    title: String,
    message: String,
    negativeText: String = getString(R.string.default_no),
    positiveText: String = getString(R.string.default_yes),
    onNegativeClicked: (() -> Unit)? = null,
    onPositiveClicked: () -> Unit
) =
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(negativeText) { _, _ -> onNegativeClicked?.invoke() }
        .setPositiveButton(positiveText) { _, _ -> onPositiveClicked() }
        .show()

fun Activity.confirm(
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
