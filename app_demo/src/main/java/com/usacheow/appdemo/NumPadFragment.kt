package com.usacheow.appdemo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.databinding.FragmentChipsGroupBinding
import com.usacheow.appdemo.databinding.FragmentErrorMessageBinding
import com.usacheow.appdemo.databinding.FragmentNumPadBinding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.Filter
import com.usacheow.coreui.uikit.organism.ErrorMessageView
import com.usacheow.coreui.uikit.organism.showOrHideError
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doWithTransitionOnParentView

class NumPadFragment : SimpleFragment<FragmentNumPadBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentNumPadBinding::inflate,
    )

    companion object {
        fun newInstance() = NumPadFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        doWithTransitionOnParentView {
            binding.numPadView.updatePadding(bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom)
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Num pad view"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }
    }
}