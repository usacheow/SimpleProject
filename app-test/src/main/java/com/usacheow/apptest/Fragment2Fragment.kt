package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment2Binding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment2Fragment : SimpleFragment<Fragment2Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment2Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from2To3Screen)
        binding.backButton.doOnClick(router::back)
    }
}