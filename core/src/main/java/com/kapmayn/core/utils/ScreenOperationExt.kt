package com.kapmayn.core.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kapmayn.coreuikit.utils.supportsLollipop

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

fun FragmentManager.removeFragment(toRemoveFragment: Fragment) {
    inTransaction {
        remove(toRemoveFragment)
    }
}

fun FragmentManager.createReplaceTransactionIn(
    @IdRes containerId: Int,
    fragment: Fragment,
    needAddToBackStack: Boolean = false
): FragmentTransaction {
    val transaction = beginTransaction().replace(containerId, fragment, fragment::class.java.simpleName)
    if (needAddToBackStack) {
        transaction.addToBackStack(null)
    }
    return transaction
}

fun FragmentManager.replaceFragmentIn(
    @IdRes containerId: Int,
    toShowFragment: Fragment,
    needAddToBackStack: Boolean = false
) {
    createReplaceTransactionIn(containerId, toShowFragment, needAddToBackStack).commit()
}

fun FragmentTransaction.addSharedElements(vararg transitionViews: View): FragmentTransaction {
    supportsLollipop {
        transitionViews.forEach { addSharedElement(it, it.transitionName) }
    }
    return this
}