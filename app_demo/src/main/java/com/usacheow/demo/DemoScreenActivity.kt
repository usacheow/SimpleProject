package com.usacheow.demo

import android.os.Bundle
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.utils.ext.RoutingTransition

class DemoScreenActivity : SimpleActivity() {

    override val layoutId = R.layout.frg_container

    private val containerDelegate by lazy { ContainerDelegate() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        containerDelegate.show(supportFragmentManager, ExampleContainerFragment.newInstance(), false, RoutingTransition())
    }

    override fun onBackPressed() {
        containerDelegate.onBackPressed(supportFragmentManager)
    }
}