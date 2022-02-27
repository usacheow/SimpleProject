package com.usacheow.corenavigation.base

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.core.navigation.ResetTo
import com.usacheow.corenavigation.R as CoreNavigationR

private const val ARGS_KEY = "ARGS_KEY"
private const val NEXT_SCREEN_DIRECTION_KEY = "NEXT_SCREEN_DIRECTION_KEY"

fun Bundle.addNextScreenDirection(direction: NavDirections) = apply {
    putParcelable(NEXT_SCREEN_DIRECTION_KEY, direction as Parcelable)
}
fun SavedStateHandle.getNextScreenDirection() = get<FeatureNavDirection>(NEXT_SCREEN_DIRECTION_KEY)
fun SavedStateHandle.requireNextScreenDirection() = requireNotNull(getNextScreenDirection())

fun Bundle.addArgs(args: Parcelable) = apply {
    putParcelable(ARGS_KEY, args)
}
fun <ARGS : Parcelable> Bundle.getArgs(): ARGS? = getParcelable(ARGS_KEY)
fun <ARGS : Parcelable> Bundle.requireArgs(): ARGS = requireNotNull(getArgs())
fun <ARGS : Parcelable> SavedStateHandle.getArgs(): ARGS? = get<ARGS>(ARGS_KEY)
fun <ARGS : Parcelable> SavedStateHandle.requireArgs(): ARGS = requireNotNull(getArgs())