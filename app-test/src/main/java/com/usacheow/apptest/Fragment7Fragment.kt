package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment7Binding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment7Fragment : SimpleFragment<Fragment7Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment7Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.backButton.doOnClick(router::back)
        binding.backToFragment2Button.doOnClick(router::backTo2Screen)
        binding.backToFragment4Button.doOnClick(router::backTo4Screen)
        binding.backToFragment6Button.doOnClick(router::backTo6Screen)
        binding.backToRootButton.doOnClick(router::backToRoot)
    }
}