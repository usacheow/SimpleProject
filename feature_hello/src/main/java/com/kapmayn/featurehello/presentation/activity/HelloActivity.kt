package com.kapmayn.featurehello.presentation.activity

import android.content.res.Resources
import android.os.Bundle
import com.kapmayn.core.presentation.activity.MvvmActivity
import com.kapmayn.core.utils.injectViewModel
import com.kapmayn.diproviders.provider.DiProvider
import com.kapmayn.featurehello.R
import com.kapmayn.featurehello.di.HelloComponent
import com.kapmayn.featurehello.presentation.router.HelloFeatureRouter
import com.kapmayn.featurehello.presentation.viewmodels.HelloViewModel
import kotlinx.android.synthetic.main.activity_hello.helloButton
import javax.inject.Inject

class HelloActivity : MvvmActivity<HelloFeatureRouter>() {

    @Inject lateinit var res: Resources
    val viewModel by lazy { injectViewModel<HelloViewModel>(viewModelFactory) }

    override val layoutId = R.layout.activity_hello

    override fun inject(diProvider: DiProvider) {
        HelloComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        helloButton.setOnClickListener { router.openWorldScreen(this) }
    }
}