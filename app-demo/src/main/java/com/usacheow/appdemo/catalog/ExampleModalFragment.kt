package com.usacheow.appdemo.catalog

import android.os.Bundle
import com.usacheow.appdemo.databinding.FragmentExampleModalBinding
import com.usacheow.coreui.fragment.SimpleModalFragment
import com.usacheow.coreui.utils.view.navigation
import com.usacheow.coreui.R as CoreUiR

class ExampleModalFragment : SimpleModalFragment<FragmentExampleModalBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentExampleModalBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleModalFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.toolbar.navigation(CoreUiR.drawable.ic_close) {
            dismiss()
        }
    }
}