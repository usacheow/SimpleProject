package com.usacheow.apptest

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : SimpleActivity<FragmentContainerBinding>(), Container {

    override val params = Params(
        viewBindingProvider = FragmentContainerBinding::inflate,
    )

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

    private var isKeyboardVisible = false

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        isKeyboardVisible = insets.isImeVisible()
        return insets
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.backStackEntryCount == 0) {
            navigateTo(TestContainerFragment.newInstance(), needAddToBackStack = false, needAnimate = false)
        }
    }

    override fun navigateTo(
        fragment: Fragment,
        needAddToBackStack: Boolean,
        needAnimate: Boolean,
        needReplace: Boolean,
    ) {
        containerDelegate.navigateTo(supportFragmentManager, fragment, needAddToBackStack, needAnimate, needReplace)
    }

    override fun resetContainer() {
        containerDelegate.resetContainer(supportFragmentManager)
    }

    override fun closeContainer() {
        finish()
    }

    override fun onBackPressed() {
        if (isKeyboardVisible) {
            windowInsetsController?.hideIme()
        } else if (!containerDelegate.onBackPressed(supportFragmentManager)) {
            finish()
        }
    }
}