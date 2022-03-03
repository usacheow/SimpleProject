package com.usacheow.featuremain.presentation.fragment

import androidx.core.view.WindowInsetsCompat
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.helper.PaddingValue
import com.usacheow.coreuiview.helper.applyTopInset
import com.usacheow.coreuiview.helper.getTopInset
import com.usacheow.featuremain.databinding.FragmentMockBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Mock2Fragment : SimpleFragment<FragmentMockBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentMockBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        return insets
    }
}