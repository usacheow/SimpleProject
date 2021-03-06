package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.usacheow.appstate.AppStateViewModel
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureonboarding.fragment.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity : SimpleActivity<FragmentContainerBinding>(), Container {

    override val params = Params(
        viewBindingProvider = FragmentContainerBinding::inflate,
    )

    private val appStateViewModel by viewModels<AppStateViewModel>()
    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

    private var isKeyboardVisible = false

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        isKeyboardVisible = insets.isImeVisible()
        return insets
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun subscribe() {
        appStateViewModel.openAuthScreenAction.observe(lifecycle) {
            navigateTo(AuthContainerFragment.newInstance(), needAddToBackStack = false, needAnimate = false)
        }

        appStateViewModel.openPinScreenAction.observe(lifecycle) {
            navigateTo(PinCodeFragment.newInstance(), false)
        }

        appStateViewModel.openOnBoardingScreenAction.observe(lifecycle) {
            navigateTo(OnBoardingFragment.newInstance(), false)
        }

        appStateViewModel.openAppScreenAction.observe(lifecycle) {
            navigateTo(BottomBarFragment.newInstance(), false)
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