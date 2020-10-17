package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.transition.TransitionSet
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.BillingActivity
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureonboarding.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity : BillingActivity(R.layout.fragment_container), IContainer {

    private val appStateViewModel by viewModels<AppStateViewModel>()

    private val containerDelegate by lazy { ContainerDelegate(layoutId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appStateViewModel.openAuthScreen.observe(this) {
            show(AuthContainerFragment.newInstance(), needAddToBackStack = false, needAnimate = false)
        }

        appStateViewModel.openPinScreen.observe(this) {
            show(PinCodeFragment.newInstance(), false)
        }

        appStateViewModel.openOnBoardingScreen.observe(this) {
            show(OnBoardingFragment.newInstance(), false)
        }

        appStateViewModel.openAppScreen.observe(this) {
            show(BottomBarFragment.newInstance(), false)
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