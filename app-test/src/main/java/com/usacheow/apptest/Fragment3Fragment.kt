package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment1Binding
import com.usacheow.apptest.databinding.Fragment3Binding
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.navigation.from
import com.usacheow.coreui.utils.navigation.openIn
import com.usacheow.coreui.utils.navigation.popBackStack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment3Fragment : SimpleFragment<Fragment3Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment3Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment3Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.go4Button.setOnClickListener {
            Fragment3FragmentDirections.actionFragment3FragmentToFragment4Fragment()
                .openIn(findNavController())
        }
        binding.go6Button.setOnClickListener {
            Fragment3FragmentDirections.actionFragment3FragmentToFragment6Fragment()
                .openIn(findNavController())
        }
        binding.backButton.setOnClickListener {
            popBackStack().from(findNavController())
        }
    }
}