package com.usacheow.featurehello.presentation.activity

import android.app.Application
import android.os.Bundle
import androidx.activity.viewModels
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.ext.doOnClick
import com.usacheow.coreui.viewmodels.ViewModelFactory
import com.usacheow.di.DiApp
import com.usacheow.featurehello.R
import com.usacheow.featurehello.di.HelloComponent
import com.usacheow.featurehello.presentation.router.HelloFeatureRouter
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import kotlinx.android.synthetic.main.activity_hello.helloButton
import javax.inject.Inject

class HelloActivity : SimpleActivity() {

    @Inject lateinit var router: HelloFeatureRouter
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<BViewModel> { viewModelFactory }

    override val layoutId = R.layout.activity_hello

    override fun inject(application: Application) {
        HelloComponent.init((application as DiApp).diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        helloButton.doOnClick { router.openWorldScreen(this) }
    }
}