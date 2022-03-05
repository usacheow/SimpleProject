package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentNumPadBinding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class NumPadFragment : SimpleFragment<FragmentNumPadBinding>() {

    @Inject lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentNumPadBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.numPadView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_back, action = router::back)
    }
}