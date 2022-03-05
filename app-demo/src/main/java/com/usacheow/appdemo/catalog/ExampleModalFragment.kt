package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.appdemo.databinding.FragmentExampleModalBinding
import com.usacheow.coreui.screen.SimpleModalFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getTopInset
import dagger.hilt.android.AndroidEntryPoint
import com.usacheow.coreuitheme.R as CoreUiThemeR

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
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_close, action = ::dismiss)
    }
}