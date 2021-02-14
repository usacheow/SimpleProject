package com.usacheow.demo

import android.os.Bundle
import com.usacheow.coreui.fragments.SimpleBottomSheetDialogFragment
import com.usacheow.coreui.fragments.SimpleModalFragment
import com.usacheow.demo.databinding.FragmentExampleModalBinding

class ExampleBottomFragment : SimpleBottomSheetDialogFragment<FragmentExampleModalBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentExampleModalBinding::inflate,
    )

    companion object {
        fun newInstance() = ExampleBottomFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Bottom fragment"
            setNavigationAction(R.drawable.ic_back) {
                dismiss()
            }
        }

        binding.fontsListView.setOnClickListener {

        }
    }
}