package com.usacheow.coreuiview.tools.resource

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors

fun Context.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
fun View.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)
fun Fragment.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)

fun Context.string(@StringRes id: Int) = resources.getString(id)
fun View.string(@StringRes id: Int) = resources.getString(id)
fun Fragment.string(@StringRes id: Int) = resources.getString(id)

fun Context.pluralString(@PluralsRes id: Int, quantity: Int) = resources.getQuantityString(id, quantity)
fun View.pluralString(@PluralsRes id: Int, quantity: Int) = resources.getQuantityString(id, quantity)
fun Fragment.pluralString(@PluralsRes id: Int, quantity: Int) = resources.getQuantityString(id, quantity)

fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun View.color(@ColorRes id: Int) = ContextCompat.getColor(context, id)
fun Fragment.color(@ColorRes id: Int) = ContextCompat.getColor(context!!, id)

fun Context.colorByAttr(@AttrRes attrRes: Int) = MaterialColors.getColor(this, attrRes, Color.BLACK)
fun View.colorByAttr(@AttrRes attrRes: Int) = MaterialColors.getColor(this, attrRes, Color.BLACK)
fun Fragment.colorByAttr(@AttrRes attrRes: Int) = MaterialColors.getColor(requireContext(), attrRes, Color.BLACK)

fun Context.dimen(@DimenRes id: Int) = resources.getDimension(id)
fun View.dimen(@DimenRes id: Int) = resources.getDimension(id)
fun Fragment.dimen(@DimenRes id: Int) = resources.getDimension(id)

val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()