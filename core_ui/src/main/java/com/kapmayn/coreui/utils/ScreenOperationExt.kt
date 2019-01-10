package com.kapmayn.coreui.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

inline fun bundleOf(block: Bundle.() -> Unit): Bundle {
    return Bundle().apply {
        block()
    }
}

fun <MODEL : Parcelable> Fragment.getParcelable(key: String): MODEL? {
    return arguments?.getParcelable(key)
}

inline fun FragmentManager.inTransaction(action: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().action().commit()
}

fun FragmentManager.createTransactionIn(
    @IdRes containerId: Int,
    fragment: Fragment,
    needAddToBackStack: Boolean = true
): FragmentTransaction {
    val transaction = beginTransaction().replace(containerId, fragment, fragment::class.java.simpleName)
    if (needAddToBackStack) {
        transaction.addToBackStack(null)
    }
    return transaction
}

fun FragmentManager.showFragmentIn(
    @IdRes containerId: Int,
    toShowFragment: Fragment,
    needAddToBackStack: Boolean = true
) {
    createTransactionIn(containerId, toShowFragment, needAddToBackStack).commit()
}