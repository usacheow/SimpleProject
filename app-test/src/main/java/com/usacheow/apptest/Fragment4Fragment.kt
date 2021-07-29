package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.apptest.databinding.Fragment4Binding
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.fragment.SimpleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment4Fragment : SimpleFragment<Fragment4Binding>() {

    override val params = Params(
        viewBindingProvider = Fragment4Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment4Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.setOnClickListener {
            findNavController().navigate(Fragment4FragmentDirections.actionFragment4FragmentToFragment5Fragment())
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}