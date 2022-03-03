package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment3Binding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.helper.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment3Fragment : SimpleFragment<Fragment3Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment3Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.go4Button.doOnClick(router::from3To4Screen)
        binding.go6Button.doOnClick(router::from3To6Screen)
        binding.backButton.doOnClick(router::back)
    }
}