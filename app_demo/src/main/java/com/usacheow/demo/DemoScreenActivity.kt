package com.usacheow.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coredata.database.Storage
import com.usacheow.coredata.database.UiMode
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.utils.view.enableLightMode
import com.usacheow.coreui.utils.view.enableNightMode
import com.usacheow.coreui.utils.view.enableSystemMode

class DemoScreenActivity : SimpleActivity(R.layout.fragment_container), IContainer {

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

    override fun onCreate(savedInstanceState: Bundle?) {

        when (Storage(this).uiMode) {
            UiMode.LIGHT -> enableLightMode()
            UiMode.NIGHT -> enableNightMode()
            UiMode.SYSTEM -> enableSystemMode()
        }

        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        show(ExampleContainerFragment.newInstance(), needAddToBackStack = false, needAnimate = false)
    }

    override fun show(fragment: Fragment, needAddToBackStack: Boolean, needAnimate: Boolean) {
        containerDelegate.show(supportFragmentManager, fragment, needAddToBackStack, needAnimate)
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