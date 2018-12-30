package com.kapmayn.featurehello.activity

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.coreui.activity.MvvmActivity
import com.kapmayn.coreui.utils.injectViewModel
import com.kapmayn.featurehello.R
import com.kapmayn.featurehello.di.HelloComponent
import com.kapmayn.featurehello.router.HelloRouter
import com.kapmayn.viewmodels.HelloViewModel

class HelloActivity : MvvmActivity<HelloRouter>() {

    val viewModel by lazy { injectViewModel<HelloViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_hello

    override fun inject(coreProvider: CoreProvider) {
        HelloComponent.init(coreProvider).inject(this)
    }
}