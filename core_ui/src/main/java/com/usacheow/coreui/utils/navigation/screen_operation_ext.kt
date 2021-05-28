package com.usacheow.coreui.utils.navigation

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

inline fun <reified ACTIVITY> Context.intentOf(noinline block: (Intent.() -> Unit)? = null): Intent {
    return Intent(this, ACTIVITY::class.java).apply {
        block?.invoke(this)
    }
}

inline fun <reified ACTIVITY> Context.start(noinline block: (Intent.() -> Unit)? = null) {
    val intent = intentOf<ACTIVITY>(block)
    startActivity(intent)
}

fun Fragment.hashTag() = "${this.hashCode()}"

fun FragmentTransaction.addSharedElements(vararg transitionViews: View): FragmentTransaction {
    transitionViews.forEach { addSharedElement(it, it.transitionName) }
    return this
}