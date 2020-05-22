package com.usacheow.coreuikit.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.IContainer
import com.usacheow.coreuikit.delegate.ContainerDelegate

abstract class ContainerActivity : SimpleActivity(), IContainer {

    override val layoutId = R.layout.frg_container

    private val containerDelegate by lazy { ContainerDelegate() }

    protected abstract fun getInitFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        containerDelegate.onCreate(supportFragmentManager, ::getInitFragment)
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, transition: TransitionSet) {
        containerDelegate.show(supportFragmentManager, fragment, needAddToBackStack, transition)
    }

    override fun reset() {
        containerDelegate.reset(supportFragmentManager)
    }

    override fun onBackPressed() {
        if (!containerDelegate.onBackPressed(supportFragmentManager)) {
            finish()
        }
    }
}