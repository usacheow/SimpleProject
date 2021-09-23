package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment1Fragment : SimpleFragment<Fragment1Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment1Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from1To2Screen)
        binding.backButton.doOnClick(router::back)
    }
}