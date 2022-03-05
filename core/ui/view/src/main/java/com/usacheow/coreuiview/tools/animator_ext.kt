package com.usacheow.coreuiview.tools

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

private const val ANIMATION_DURATION = 100L

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

fun Fragment.startFragmentTransition(duration: Long = ANIMATION_DURATION, block: () -> Unit) {
    val view = (view as? ViewGroup) ?: return
    TransitionManager.endTransitions(view)
    TransitionManager.beginDelayedTransition(view, ScreenUpdateTransition(duration))
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