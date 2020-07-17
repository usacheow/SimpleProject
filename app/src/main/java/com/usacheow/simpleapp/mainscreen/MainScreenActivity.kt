package com.usacheow.simpleapp.mainscreen

import android.app.Application
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.BillingActivity
import com.usacheow.coreui.base.IContainer
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.livedata.subscribe
import com.usacheow.coreui.viewmodels.ViewModelFactory
import com.usacheow.di.DiApp
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.featureauth.presentation.fragment.PinCodeFragment
import com.usacheow.featureonboarding.OnBoardingFragment
import com.usacheow.simpleapp.mainscreen.di.MainScreenComponent
import javax.inject.Inject

class MainScreenActivity : BillingActivity(), IContainer {

    override val layoutId = R.layout.frg_container

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val appStateViewModel by viewModels<AppStateViewModel> { viewModelFactory }

    private val containerDelegate by lazy { ContainerDelegate(layoutId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appStateViewModel.openAuthScreen.subscribe(this) {
            show(AuthContainerFragment.newInstance(), false)
        }

        appStateViewModel.openPinScreen.subscribe(this) {
            show(PinCodeFragment.newInstance(), false)
        }

        appStateViewModel.openOnBoardingScreen.subscribe(this) {
            show(OnBoardingFragment.newInstance(), false)
        }

        appStateViewModel.openAppScreen.subscribe(this) {
            show(BottomBarFragment.newInstance(), false)
        }
    }

    override fun inject(application: Application) {
        MainScreenComponent.init((application as DiApp).diProvider).inject(this)
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