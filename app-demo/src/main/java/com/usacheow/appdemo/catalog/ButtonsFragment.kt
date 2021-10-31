package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.R as CoreUiR
import com.usacheow.appdemo.databinding.FragmentButtonsBinding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset

class ButtonsFragment : SimpleFragment<FragmentButtonsBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentButtonsBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.viewsScrollView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Buttons"
            setNavigationAction(CoreUiR.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }
    }
}