package com.kotlinandroidstarter.app.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinandroidstarter.app.R

fun RecyclerView.initWithDefaults(context: Context) {
    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(context)
}

inline fun <reified VH : RecyclerView.ViewHolder> RecyclerView.initWithDefaults(
    context: Context,
    adapter: RecyclerView.Adapter<VH>) {
    
    this.initWithDefaults(context)
    this.adapter = adapter
    
}

fun AppCompatActivity.setUpArrowColor(
    @ColorInt color: Int,
    @DrawableRes drawableResId: Int = R.drawable.zzz_arrow_left) {
    
    val drawable = ContextCompat.getDrawable(this, drawableResId)
    drawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    
    supportActionBar?.setHomeAsUpIndicator(drawable)
    
}

fun Fragment.setUpArrowColor(
    @ColorInt color: Int,
    @DrawableRes drawableResId: Int = R.drawable.zzz_arrow_left) {
    
    val host =
        if (activity is AppCompatActivity) activity as AppCompatActivity
        else throw RuntimeException("Parent activity must be AppCompatActivity")
    
    val drawable = ContextCompat.getDrawable(host, drawableResId)
    drawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    
    host.supportActionBar?.setHomeAsUpIndicator(drawable)
    
}

fun View.setMarginBottom(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
    bottomMargin = value
}
