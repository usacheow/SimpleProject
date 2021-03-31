package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.usacheow.appshared.AppStateViewModel
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.BillingActivity
import com.usacheow.coreui.base.Container
import com.usacheow.coreui.databinding.FragmentContainerBinding
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.utils.observe
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureonboarding.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity : BillingActivity<FragmentContainerBinding>(), Container {

    override val params = Params(
        viewBindingProvider = FragmentContainerBinding::inflate,
    )

    private val appStateViewModel by viewModels<AppStateViewModel>()

    private val containerDelegate by lazy { ContainerDelegate(javaClass.simpleName) }

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

    override fun navigateTo(fragment: Fragment, needAddToBackStack: Boolean, needAnimate: Boolean) {
        containerDelegate.showFragment(supportFragmentManager, fragment, needAddToBackStack, needAnimate)
    }

    override fun resetContainer() {
        containerDelegate.resetContainer(supportFragmentManager)
    }

    override fun closeContainer() {
        finish()
    }

    override fun onBackPressed() {
        if (!containerDelegate.onBackPressed(supportFragmentManager)) {
            finish()
        }
    }
}