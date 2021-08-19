package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.apptest.databinding.Fragment2Binding
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.fragment.SimpleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment2Fragment : SimpleFragment<Fragment2Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment2Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment2Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.setOnClickListener {
            findNavController().navigate(Fragment2FragmentDirections.actionFragment2FragmentToFragment3Fragment())
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}