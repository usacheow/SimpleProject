package com.usacheow.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.demo.databinding.FragmentFontsBinding

class FontsFragment : SimpleFragment<FragmentFontsBinding>() {

    companion object {
        fun newInstance() = FontsFragment()
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFontsBinding {
        return FragmentFontsBinding.inflate(inflater, container, false)
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.fontsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Fonts samples"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.fontsListView.setOnClickListener {

        }
    }
}