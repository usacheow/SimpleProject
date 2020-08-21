package com.usacheow.coreui.utils.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

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

fun Fragment.doWithTransitionOnParentView(block: () -> Unit) {
    val view = parentFragment?.view ?: view
    (view as? ViewGroup)?.doWithTransition(block)
}

fun View.doWithTransition(block: () -> Unit) {
    (this as? ViewGroup)?.doWithTransition(block)
}

fun ViewGroup.doWithTransition(block: () -> Unit) {
    TransitionManager.beginDelayedTransition(this, ScreenUpdateTransition())
    block()
}

class ScreenUpdateTransition : TransitionSet() {

    init {
        ordering = ORDERING_TOGETHER
        addTransition(Fade())
        addTransition(ChangeBounds())
    }
}

class RoutingTransition : AutoTransition() {
    init {
        duration = 100
    }
}