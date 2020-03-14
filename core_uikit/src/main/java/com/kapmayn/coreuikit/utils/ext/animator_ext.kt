package com.kapmayn.coreuikit.utils.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

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
    val autoTransition = AutoTransition().apply {
        this.duration = duration
    }
    TransitionManager.beginDelayedTransition(this, autoTransition)
    block()
}