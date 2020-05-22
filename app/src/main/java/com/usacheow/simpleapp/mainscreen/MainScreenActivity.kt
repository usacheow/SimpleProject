package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coreuikit.AppStateViewModel
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.activity.BillingActivity
import com.usacheow.coreuikit.delegate.ContainerDelegate
import com.usacheow.coreuikit.utils.ext.RoutingTransition
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.demo.ExampleContainerFragment
import com.usacheow.diprovider.DiProvider
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.simpleapp.mainscreen.di.MainScreenComponent
import javax.inject.Inject

class MainScreenActivity : BillingActivity() {

    override val layoutId = R.layout.frg_container

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val appStateViewModel by injectViewModel<AppStateViewModel> { viewModelFactory }

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

        appStateViewModel.openDemoScreen.subscribe(this) {
            show(ExampleContainerFragment.newInstance())
        }
    }

    override fun inject(diProvider: DiProvider) {
        MainScreenComponent.init(diProvider).inject(this)
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