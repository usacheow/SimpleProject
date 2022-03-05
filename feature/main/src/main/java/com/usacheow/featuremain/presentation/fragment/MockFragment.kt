package com.usacheow.featuremain.presentation.fragment

import androidx.core.view.WindowInsetsCompat
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.featuremain.databinding.FragmentMockBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MockFragment : SimpleFragment<FragmentMockBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentMockBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        return insets
    }
}