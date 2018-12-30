package com.kapmayn.coreui.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

inline fun <reified ACTIVITY> Context.intentOf(noinline block: (Intent.() -> Unit)? = null): Intent {
    return Intent(this, ACTIVITY::class.java).apply {
        block?.invoke(this)
    }
}

inline fun <reified ACTIVITY> Context.start(noinline block: (Intent.() -> Unit)? = null) {
    val intent = intentOf<ACTIVITY>(block)
    startActivity(intent)
}

inline fun bundleOf(block: Bundle.() -> Unit): Bundle {
    return Bundle().apply {
        block()
    }
}

fun <MODEL : Parcelable> Fragment.getParcelable(key: String): MODEL? {
    return arguments?.getParcelable(key)
}