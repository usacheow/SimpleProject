package com.usacheow.coreui.utils.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*

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

fun Fragment.startParentFragmentTransition(duration: Long = 100, block: () -> Unit) {
    parentFragment?.startFragmentTransition(duration, block)
}

fun Fragment.startFragmentTransition(duration: Long = 100, block: () -> Unit) {
    val view = (view as? ViewGroup) ?: return
    TransitionManager.beginDelayedTransition(view, ScreenUpdateTransition(duration))
    block()
}

fun View.doWithTransition(
    duration: Long = 100,
    transition: Transition = ScreenUpdateTransition(duration),
    block: () -> Unit
) {
    (this as? ViewGroup)?.doWithTransition(duration, transition, block)
}

fun ViewGroup.doWithTransition(
    duration: Long = 100,
    transition: Transition = ScreenUpdateTransition(duration),
    block: () -> Unit
) {
    TransitionManager.beginDelayedTransition(this, transition)
    block()
}

class ScreenUpdateTransition(duration: Long) : TransitionSet() {

    init {
        this.duration = duration
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