package com.usacheow.apptest

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.usacheow.apptest.databinding.FragmentCoroutinesBinding
import com.usacheow.coreui.screen.SimpleFragment
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
        binding.header.root.apply {
            title = "Test Screen"
        }

        binding.startButton.setOnClickListener {
            startBackgroundTask()
        }
    }

    private fun startBackgroundTask() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                var x = 1_000_000
                (0..1_000_000_00).forEach {
                    x--
                }
            }
            Toast.makeText(requireContext(), "finish", Toast.LENGTH_SHORT).show()
        }
    }
}