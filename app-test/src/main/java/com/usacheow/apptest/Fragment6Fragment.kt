package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.apptest.databinding.Fragment6Binding
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.fragment.SimpleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment6Fragment : SimpleFragment<Fragment6Binding>() {

    override val params = Params(
        viewBindingProvider = Fragment6Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment6Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.setOnClickListener {
            findNavController().navigate(Fragment6FragmentDirections.actionFragment6FragmentToFragment7Fragment())
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}