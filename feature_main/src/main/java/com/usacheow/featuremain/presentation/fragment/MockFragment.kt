package com.usacheow.featuremain.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.featuremain.databinding.FragmentMockBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MockFragment : SimpleFragment<FragmentMockBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentMockBinding::inflate,
    )

    companion object {
        fun newInstance() = MockFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.header.root.applyInsets(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.title = "Mock"
    }
}