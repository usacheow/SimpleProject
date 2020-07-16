package com.usacheow.simpleapp.mainscreen

import android.app.Application
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.BillingActivity
import com.usacheow.coreui.delegate.ContainerDelegate
import com.usacheow.coreui.livedata.subscribe
import com.usacheow.coreui.utils.ext.RoutingTransition
import com.usacheow.coreui.viewmodels.ViewModelFactory
import com.usacheow.diprovider.DiApp
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.simpleapp.mainscreen.di.MainScreenComponent
import javax.inject.Inject

class MainScreenActivity : BillingActivity() {

    override val layoutId = R.layout.frg_container

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val appStateViewModel by viewModels<AppStateViewModel> { viewModelFactory }

    private val containerDelegate by lazy { ContainerDelegate() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appStateViewModel.openAuthScreen.subscribe(this) {
            show(AuthContainerFragment.newInstance())
//            show(AuthContainerFragment.newInstance())
//            show(PinCodeFragment.newInstance())
        }

        appStateViewModel.openAppScreen.subscribe(this) {
            show(BottomBarFragment.newInstance())
        }
    }

    override fun inject(application: Application) {
        MainScreenComponent.init((application as DiApp).diProvider).inject(this)
    }

    private fun show(fragment: Fragment, needAddToBackstack: Boolean = false) {
        containerDelegate.show(supportFragmentManager, fragment, needAddToBackstack, RoutingTransition())
    }

    override fun onBackPressed() {
        if (!containerDelegate.onBackPressed(supportFragmentManager)) {
            finish()
        }
    }
}