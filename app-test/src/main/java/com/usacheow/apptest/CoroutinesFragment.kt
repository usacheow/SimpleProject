package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.doOnClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesFragment : SimpleFragment<FragmentCoroutinesBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentCoroutinesBinding::inflate,
    )

    companion object {
        fun newInstance() = CoroutinesFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.apply {
            title = "Test Screen"
        }

        binding.startButton.doOnClick {
            startBackgroundTask()
        }
    }

    private fun startBackgroundTask() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                var x = 1_000_000
                repeat((0..1_000_000_00).count()) {
                    x--
                }
            }
            Toast.makeText(requireContext(), "finish", Toast.LENGTH_SHORT).show()
        }
    }
}