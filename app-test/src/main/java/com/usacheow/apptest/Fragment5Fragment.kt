package com.usacheow.apptest

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.usacheow.apptest.databinding.Fragment5Binding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.navigation.from
import com.usacheow.coreui.utils.navigation.openIn
import com.usacheow.coreui.utils.navigation.popBackStack
import com.usacheow.coreui.utils.navigation.replaceAllTo
import com.usacheow.coreui.utils.navigation.screen

class Fragment5Fragment : SimpleFragment<Fragment5Binding>() {

    override val defaultParams = Params(
        viewBindingProvider = Fragment5Binding::inflate,
    )

    companion object {
        fun newInstance() = Fragment5Fragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.goButton.setOnClickListener {
            screen(R.id.fragment7Fragment)
                .replaceAllTo(R.id.fragment4Fragment)
                .openIn(findNavController())
        }
        binding.backButton.setOnClickListener {
            popBackStack().from(findNavController())
        }
    }
}