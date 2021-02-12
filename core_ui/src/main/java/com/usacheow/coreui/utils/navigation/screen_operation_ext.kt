package com.usacheow.coreui.utils.navigation

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.system.ifSupportLollipop

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

inline fun FragmentManager.inTransaction(action: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().action().commit()
}

fun FragmentManager.replaceFragmentIn(
    @IdRes containerId: Int,
    toShowFragment: Fragment,
    needAddToBackStack: Boolean = false,
    tag: String = toShowFragment.hashTag()
) {
    val transaction = beginTransaction().replace(containerId, toShowFragment, tag)
    if (needAddToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

fun FragmentTransaction.addSharedElements(vararg transitionViews: View): FragmentTransaction {
    ifSupportLollipop {
        transitionViews.forEach { addSharedElement(it, it.transitionName) }
    }
    return this
}

fun FragmentTransaction.addSharedElementsFrom(fragment: SimpleFragment<*>?): FragmentTransaction {
    fragment?.let { addSharedElements(*it.getSharedViews().toTypedArray()) }
    return this
}