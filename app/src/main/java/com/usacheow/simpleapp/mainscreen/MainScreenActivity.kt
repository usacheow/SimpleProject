package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.usacheow.coreuikit.AppStateViewModel
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.activity.SimpleActivity
import com.usacheow.coreuikit.base.DefaultTransition
import com.usacheow.coreuikit.base.IBackListener
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.addSharedElementsFrom
import com.usacheow.coreuikit.utils.ext.inTransaction
import com.usacheow.coreuikit.utils.ifSupportLollipop
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.coreuikit.viewmodels.livedata.subscribe
import com.usacheow.demo.ExampleContainerFragment
import com.usacheow.diprovider.DiProvider
import com.usacheow.featureauth.presentation.fragment.AuthContainerFragment
import com.usacheow.simpleapp.mainscreen.di.MainScreenComponent
import javax.inject.Inject

class MainScreenActivity : SimpleActivity() {

    override val layoutId = R.layout.frg_container

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val appStateViewModel by injectViewModel<AppStateViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appStateViewModel.openAuthScreen.subscribe(this) {
            show(AuthContainerFragment.newInstance())
//            show(AuthContainerFragment.newInstance())
//            show(PinCodeFragment.newInstance())
        }

        appStateViewModel.openContentScreen.subscribe(this) {
            show(BottomBarFragment.newInstance())
        }

        appStateViewModel.openDemoScreen.subscribe(this) {
            show(ExampleContainerFragment.newInstance())
        }
    }

    override fun inject(diProvider: DiProvider) {
        MainScreenComponent.init(diProvider).inject(this)
    }

    private fun show(fragment: Fragment) {
        val activeFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        supportFragmentManager.inTransaction {
            ifSupportLollipop {
                fragment.sharedElementEnterTransition = DefaultTransition()
                fragment.sharedElementReturnTransition = DefaultTransition()
            }
            addSharedElementsFrom(activeFragment as? SimpleFragment)
            replace(R.id.fragmentContainer, fragment)
            this
        }
    }

    override fun onBackPressed() {
        val activeFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (activeFragment is IBackListener && activeFragment.onBackPressed()) {
        } else {
            finish()
        }
    }
}