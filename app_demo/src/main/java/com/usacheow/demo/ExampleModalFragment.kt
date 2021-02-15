package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.fragments.SimpleModalFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.navigation
import com.usacheow.demo.databinding.FragmentExampleBinding
import com.usacheow.demo.databinding.FragmentExampleModalBinding
import com.usacheow.demo.databinding.FragmentFontsBinding

class ExampleModalFragment : SimpleModalFragment<FragmentExampleModalBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentExampleModalBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleModalFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.toolbar.navigation(R.drawable.ic_close) {
            dismiss()
        }
    }
}