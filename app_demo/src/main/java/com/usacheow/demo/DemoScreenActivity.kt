package com.usacheow.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.delegate.ContainerDelegate

class DemoScreenActivity : SimpleActivity(R.layout.frg_container), IContainer {

    private val containerDelegate by lazy { ContainerDelegate(layoutId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        show(ExampleContainerFragment.newInstance(), false)
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