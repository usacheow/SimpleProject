package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentButtonsBinding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue

class ButtonsFragment : SimpleFragment<FragmentButtonsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentButtonsBinding::inflate,
    )

    companion object {
        fun newInstance() = ButtonsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.header.root.applyInsets(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
        binding.viewsScrollView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
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