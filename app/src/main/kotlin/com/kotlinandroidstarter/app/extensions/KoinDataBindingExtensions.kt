package com.kotlinandroidstarter.app.extensions

import android.content.Context
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.get

//region When Empty

/**
 * Removes a [view] when [data] is null
 */
@BindingAdapter("goneWhenNull")
fun goneWhenNull(view: View, data: Any?) {
    view.visibility = if (data == null) View.GONE else View.VISIBLE
}

/**
 * Makes a [view] invisible when [data] is null
 */
@BindingAdapter("hiddenWhenNull")
fun hiddenWhenNull(view: View, data: Any?) {
    view.visibility = if (data == null) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("goneWhenEmpty")
fun goneWhenEmpty(view: View, data: String?) {
    view.visibility = if (data.isNullOrBlank()) View.GONE else View.VISIBLE
}

@BindingAdapter("hiddenWhenEmpty")
fun hiddenWhenEmpty(view: View, data: String?) {
    view.visibility = if (data.isNullOrBlank()) View.INVISIBLE else View.VISIBLE
}

//endregion When Empty

//region When Blank

/**
 * Removes a [view] when [data] is null or blank
 */
@BindingAdapter("goneWhenBlank")
fun goneWhenBlank(view: View, data: CharSequence?) {
    view.visibility = if (data.isNullOrBlank()) View.GONE else View.VISIBLE
}

/**
 * Makes a [view] invisible when [data] is null or blank
 */
@BindingAdapter("hiddenWhenBlank")
fun hiddenWhenBlank(view: View, data: String?) {
    view.visibility = if (data.isNullOrBlank()) View.INVISIBLE else View.VISIBLE
}

//endregion When Blank

//region Unless

/**
 * Removes a [view] until [data] is a truthy value
 */
@BindingAdapter("goneUnless")
fun goneUnless(view: View, data: Boolean) {
    view.visibility = if (data) View.VISIBLE else View.GONE
}

/**
 * Hides a [view] until [data] is a truthy value
 */
@BindingAdapter("hiddenUnless")
fun hiddenUnless(view: View, data: Boolean) {
    view.visibility = if (data) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("goneUnlessBlank")
fun goneUnlessBlank(view: View, data: String?) {
    view.visibility = if (data.isNullOrBlank()) View.VISIBLE else View.GONE
}

//endregion Unless

//region When

/**
 * Removes a [view] when [data] is a truthy value
 */
@BindingAdapter("goneWhen")
fun goneWhen(view: View, data: Boolean) {
    view.visibility = if (data) View.GONE else View.VISIBLE
}

/**
 * Makes a [view] invisible when [data] is a truthy value
 */
@BindingAdapter("hiddenWhen")
fun hiddenWhen(view: View, data: Boolean) {
    view.visibility = if (data) View.INVISIBLE else View.VISIBLE
}

//endregion When

//region Uncategorized

fun ViewModel.getString(@StringRes resourceId: Int) : String =
    (object : KoinComponent {}).get<Context>().getString(resourceId)

fun ViewModel.getString(@StringRes resourceId: Int, vararg params: Any) : String =
    (object : KoinComponent {}).get<Context>().getString(resourceId, *params)

fun ViewModel.getStringArray(@ArrayRes resourceId: Int): Array<String> =
    (object : KoinComponent {}).get<Context>().resources.getStringArray(resourceId)

@ColorInt
fun ViewModel.getColor(@ColorRes resourceId: Int) : Int =
    ContextCompat.getColor((object : KoinComponent {}).get<Context>().applicationContext, resourceId)

//endregion Uncategorized
