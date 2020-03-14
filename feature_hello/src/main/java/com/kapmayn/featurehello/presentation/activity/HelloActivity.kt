package com.kapmayn.featurehello.presentation.activity

import android.os.Bundle
import com.kapmayn.core.provider.DiProvider
import com.kapmayn.coreuikit.activity.SimpleActivity
import com.kapmayn.coreuikit.viewmodels.ViewModelFactory
import com.kapmayn.coreuikit.viewmodels.injectViewModel
import com.kapmayn.featurehello.R
import com.kapmayn.featurehello.di.HelloComponent
import com.kapmayn.featurehello.presentation.router.HelloFeatureRouter
import com.kapmayn.featurehello.presentation.viewmodels.HelloViewModel
import kotlinx.android.synthetic.main.activity_hello.helloButton
import javax.inject.Inject

class HelloActivity : SimpleActivity() {

    @Inject lateinit var router: HelloFeatureRouter
    @Inject lateinit var viewModelFactory: ViewModelFactory
    val viewModel by lazy { injectViewModel<HelloViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_hello

    override fun inject(diProvider: DiProvider) {
        HelloComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        helloButton.setOnClickListener { router.openWorldScreen(this) }
    }
}