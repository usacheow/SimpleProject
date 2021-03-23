package com.usacheow.appdemo.catalog

import android.os.Bundle
import com.usacheow.appdemo.R
import com.usacheow.coreui.fragment.SimpleModalFragment
import com.usacheow.appdemo.databinding.FragmentExampleModalBinding
import com.usacheow.coreui.utils.view.navigation

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