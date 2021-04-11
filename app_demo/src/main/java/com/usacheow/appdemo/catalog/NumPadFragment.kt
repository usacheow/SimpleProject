package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentNumPadBinding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue

class NumPadFragment : SimpleFragment<FragmentNumPadBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentNumPadBinding::inflate,
    )

    companion object {
        fun newInstance() = NumPadFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.header.root.applyInsets(insets.getInsets(WindowInsetsCompat.Type.systemBars()).top)
        binding.numPadView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Num pad view"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }
    }
}