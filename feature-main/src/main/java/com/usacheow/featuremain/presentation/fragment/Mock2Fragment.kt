package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getTopInset
import com.usacheow.featuremain.databinding.FragmentMockBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Mock2Fragment : SimpleFragment<FragmentMockBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentMockBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.title = "Mock"
    }
}