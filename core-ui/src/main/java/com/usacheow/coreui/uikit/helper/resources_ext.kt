package com.usacheow.coreui.uikit.helper

import android.app.Activity
import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun View.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)
fun Fragment.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)
fun Activity.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun View.string(@StringRes id: Int) = resources.getString(id)
fun Fragment.string(@StringRes id: Int) = resources.getString(id)
fun Activity.string(@StringRes id: Int) = resources.getString(id)

fun View.pluralString(@PluralsRes id: Int, quantity: Int) = resources.getQuantityString(id, quantity)
fun Fragment.pluralString(@PluralsRes id: Int, quantity: Int) = resources.getQuantityString(id, quantity)
fun Activity.pluralString(@PluralsRes id: Int, quantity: Int) = resources.getQuantityString(id, quantity)

fun View.color(@ColorRes id: Int) = ContextCompat.getColor(context, id)
fun Fragment.color(@ColorRes id: Int) = ContextCompat.getColor(context!!, id)
fun Activity.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun View.dimen(@DimenRes id: Int) = resources.getDimension(id)
fun Fragment.dimen(@DimenRes id: Int) = resources.getDimension(id)
fun Activity.dimen(@DimenRes id: Int) = resources.getDimension(id)

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()