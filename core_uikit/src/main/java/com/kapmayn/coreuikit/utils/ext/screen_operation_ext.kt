package com.kapmayn.coreuikit.utils.ext

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kapmayn.coreuikit.utils.ifSupportLollipop

inline fun <reified ACTIVITY> Context.intentOf(noinline block: (Intent.() -> Unit)? = null): Intent {
    return Intent(this, ACTIVITY::class.java).apply {
        block?.invoke(this)
    }
}

inline fun <reified ACTIVITY> Context.start(noinline block: (Intent.() -> Unit)? = null) {
    val intent = intentOf<ACTIVITY>(block)
    startActivity(intent)
}

fun <MODEL : Parcelable> Fragment.getParcelable(key: String): MODEL? {
    return arguments?.getParcelable(key)
}

fun Fragment.hashTag() = "${this.hashCode()}"

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
    needAddToBackStack: Boolean = false,
    tag: String = fragment.hashTag()
): FragmentTransaction {
    val transaction = beginTransaction().replace(containerId, fragment, tag)
    if (needAddToBackStack) {
        transaction.addToBackStack(null)
    }
    return transaction
}

fun FragmentManager.replaceFragmentIn(
    @IdRes containerId: Int,
    toShowFragment: Fragment,
    needAddToBackStack: Boolean = false,
    tag: String = toShowFragment.hashTag()
) {
    createReplaceTransactionIn(containerId, toShowFragment, needAddToBackStack, tag).commit()
}

fun FragmentTransaction.addSharedElements(vararg transitionViews: View): FragmentTransaction {
    ifSupportLollipop {
        transitionViews.forEach { addSharedElement(it, it.transitionName) }
    }
    return this
}