package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentButtonsBinding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class ButtonsFragment : SimpleFragment<FragmentButtonsBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentButtonsBinding::inflate,
    )

    companion object {
        fun newInstance() = ButtonsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.viewsScrollView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Buttons"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }
    }
}