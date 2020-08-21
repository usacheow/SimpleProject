package com.usacheow.featurehello.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.featurehello.R
import com.usacheow.featurehello.presentation.router.HelloFeatureRouter
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_hello.helloButton
import javax.inject.Inject

@AndroidEntryPoint
class HelloActivity : SimpleActivity(R.layout.activity_hello) {

    @Inject lateinit var router: HelloFeatureRouter

    private val viewModel by viewModels<BViewModel>()

    override fun setupViews(savedInstanceState: Bundle?) {
        helloButton.doOnClick { router.openWorldScreen(this) }
    }
}