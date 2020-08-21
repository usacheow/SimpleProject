package com.usacheow.coreui.utils.keyboard

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver

private const val HEIGHT_DIFF = 300

class KeyboardStateProvider {

    private val listener = CustomListener()
    private var root: View? = null

    fun subscribe(view: View, stateListener: KeyboardStateListener) {
        root = view
        listener.view = view
        listener.keyboardExpandListener = stateListener
        root?.viewTreeObserver?.addOnGlobalLayoutListener(listener)
    }

    fun unsubscribe() {
        root = null
        root?.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
        listener.keyboardExpandListener = null
        listener.view = null
    }

    private class CustomListener : ViewTreeObserver.OnGlobalLayoutListener {

        var view: View? = null
        var keyboardExpandListener: KeyboardStateListener? = null

        override fun onGlobalLayout() {
            view?.run {
                val react = Rect().also { getWindowVisibleDisplayFrame(it) }
                val heightDiff = rootView.height - (react.bottom - react.top)

                if (heightDiff > HEIGHT_DIFF) {
                    keyboardExpandListener?.onKeyboardExpand()
                } else {
                    keyboardExpandListener?.onKeyboardCollapse()
                }
            }
        }

    }
}

interface KeyboardStateListener {

    fun onKeyboardExpand()

    fun onKeyboardCollapse()
}

open class SimpleKeyboardStateListener(
    var onKeyboardCollapsed: (() -> Unit) = {},
    var onKeyboardExpanded: (() -> Unit) = {}
) : KeyboardStateListener {

    private var isExpand: Boolean? = null

    override fun onKeyboardCollapse() {
        if (isExpand != false) {
            onKeyboardCollapsed()
            isExpand = false
        }
    }

    override fun onKeyboardExpand() {
        if (isExpand != true) {
            onKeyboardExpanded()
            isExpand = true
        }
    }
}