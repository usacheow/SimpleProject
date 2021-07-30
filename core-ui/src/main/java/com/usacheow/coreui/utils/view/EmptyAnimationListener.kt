package com.usacheow.coreui.utils.view

import android.view.animation.Animation

open class EmptyAnimationListener : Animation.AnimationListener {

    override fun onAnimationStart(animation: Animation?) = Unit

    override fun onAnimationEnd(animation: Animation?) = Unit

    override fun onAnimationRepeat(animation: Animation?) = Unit
}