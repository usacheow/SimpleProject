package com.usacheow.coreui.utils.view

import android.app.Activity
import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun View.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)

fun View.string(@StringRes id: Int) = resources.getString(id)

fun View.color(@ColorRes id: Int) = ContextCompat.getColor(context, id)

fun View.dimen(@DimenRes id: Int) = resources.getDimension(id)

fun Fragment.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)

fun Fragment.string(@StringRes id: Int) = resources.getString(id)

fun Fragment.color(@ColorRes id: Int) = ContextCompat.getColor(context!!, id)

fun Fragment.dimen(@DimenRes id: Int) = resources.getDimension(id)

fun Activity.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun Activity.string(@StringRes id: Int) = resources.getString(id)

fun Activity.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Activity.dimen(@DimenRes id: Int) = resources.getDimension(id)

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()