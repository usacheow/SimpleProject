package com.usacheow.apptest

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment5Binding
import com.usacheow.coreui.fragment.SimpleFragment

class Fragment5Fragment : SimpleFragment<Fragment5Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment5Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment5Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.setOnClickListener {
            findNavController().popBackStack(R.id.fragment4Fragment, inclusive = true)
            findNavController().navigate(ActionOnlyNavDirections(R.id.fragment7Fragment))
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}