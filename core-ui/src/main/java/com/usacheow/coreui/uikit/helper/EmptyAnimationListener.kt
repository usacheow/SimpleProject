package com.usacheow.coreui.uikit.helper

import android.view.animation.Animation

open class EmptyAnimationListener : Animation.AnimationListener {

    override fun onAnimationStart(animation: Animation?) = Unit

    override fun onAnimationEnd(animation: Animation?) = Unit

    override fun onAnimationRepeat(animation: Animation?) = Unit
}