package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.fragment.SimpleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment1Fragment : SimpleFragment<Fragment1Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment1Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment1Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.setOnClickListener {
            findNavController().navigate(Fragment1FragmentDirections.actionFragment1FragmentToFragment2Fragment())
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}