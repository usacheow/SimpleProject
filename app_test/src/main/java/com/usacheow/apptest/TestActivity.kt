package com.usacheow.apptest

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : SimpleActivity<FragmentContainerBinding>(), Container {

    override val params = Params(
        viewBindingProvider = FragmentContainerBinding::inflate,
    )

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.backStackEntryCount == 0) {
            show(TestFragment.newInstance(), needAddToBackStack = false, needAnimate = false)
        }
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