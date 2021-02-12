package com.usacheow.featurehello.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.featurehello.databinding.ActivityHelloBinding
import com.usacheow.featurehello.presentation.router.HelloFeatureRouter
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HelloActivity : SimpleActivity<ActivityHelloBinding>() {

    @Inject lateinit var router: HelloFeatureRouter

    private val viewModel by viewModels<BViewModel>()

    override fun createViewBinding() = ActivityHelloBinding.inflate(layoutInflater)

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.helloButton.doOnClick { router.openWorldScreen(this) }
    }
}