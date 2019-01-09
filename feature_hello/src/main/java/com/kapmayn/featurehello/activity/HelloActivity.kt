package com.kapmayn.featurehello.activity

import android.content.res.Resources
import android.os.Bundle
import com.kapmayn.core.di.CoreProvider
import com.kapmayn.coreui.activity.MvvmActivity
import com.kapmayn.coreui.utils.injectViewModel
import com.kapmayn.featurehello.R
import com.kapmayn.featurehello.di.HelloComponent
import com.kapmayn.featurehello.router.HelloFeatureRouter
import com.kapmayn.viewmodels.HelloViewModel
import kotlinx.android.synthetic.main.activity_hello.helloButton
import javax.inject.Inject

class HelloActivity : MvvmActivity<HelloFeatureRouter>() {

    @Inject lateinit var res: Resources
    val viewModel by lazy { injectViewModel<HelloViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_hello

    override fun inject(coreProvider: CoreProvider) {
        HelloComponent.init(coreProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        helloButton.setOnClickListener { router.openWorldScreen(this) }
    }
}