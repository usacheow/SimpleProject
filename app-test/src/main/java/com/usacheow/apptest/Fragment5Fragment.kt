package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment5Binding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment5Fragment : SimpleFragment<Fragment5Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment5Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from5To7Screen)
        binding.backButton.doOnClick(router::back)
        binding.backToRootButton.doOnClick(router::backToRoot)
    }
}