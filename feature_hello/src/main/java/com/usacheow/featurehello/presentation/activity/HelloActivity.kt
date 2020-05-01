package com.usacheow.featurehello.presentation.activity

import android.os.Bundle
import com.usacheow.coreuikit.activity.SimpleActivity
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.diprovider.DiProvider
import com.usacheow.featurehello.R
import com.usacheow.featurehello.di.HelloComponent
import com.usacheow.featurehello.presentation.router.HelloFeatureRouter
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import kotlinx.android.synthetic.main.activity_hello.helloButton
import javax.inject.Inject

class HelloActivity : SimpleActivity() {

    @Inject lateinit var router: HelloFeatureRouter
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy { injectViewModel<BViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_hello

    override fun inject(diProvider: DiProvider) {
        HelloComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        helloButton.doOnClick { router.openWorldScreen(this) }
    }
}