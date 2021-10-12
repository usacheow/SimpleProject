package com.usacheow.apptest

import android.os.Bundle
import com.usacheow.apptest.databinding.Fragment4Binding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.doOnClick
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Fragment4Fragment : SimpleFragment<Fragment4Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment4Binding::inflate,
    )

    @Inject lateinit var router: TestRouter

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.doOnClick(router::from4To5Screen)
        binding.backButton.doOnClick(router::back)
    }
}