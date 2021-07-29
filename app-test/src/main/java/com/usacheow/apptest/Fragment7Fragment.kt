package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.apptest.databinding.Fragment7Binding
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.fragment.SimpleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment7Fragment : SimpleFragment<Fragment7Binding>() {

    override val params = Params(
        viewBindingProvider = Fragment7Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment7Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}