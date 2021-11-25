package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.appdemo.databinding.FragmentExampleModalBinding
import com.usacheow.coreui.screen.SimpleModalFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getTopInset
import dagger.hilt.android.AndroidEntryPoint
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class ExampleModalFragment : SimpleModalFragment<FragmentExampleModalBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentExampleModalBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiR.drawable.ic_close, action = ::dismiss)
    }
}