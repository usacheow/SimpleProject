package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment6Binding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment6Fragment : SimpleFragment<Fragment6Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment6Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from6To7Screen)
        binding.backButton.doOnClick(router::back)
    }
}