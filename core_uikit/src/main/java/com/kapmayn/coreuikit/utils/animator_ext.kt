package com.kapmayn.coreuikit.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewGroup

fun Animator.withEndAction(action: () -> Unit): Animator {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            action()
        }
    })
    return this
}

fun Animator.withStartAction(action: () -> Unit): Animator {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            action()
        }
    })
    return this
}

fun ViewGroup.doWithAutoTransition(
    duration: Long = 100L,
    block: () -> Unit
) {
    doWithAutoTransition(duration) {
        block()
    }
}