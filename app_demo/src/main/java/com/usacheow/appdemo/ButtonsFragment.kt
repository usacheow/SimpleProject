package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.databinding.FragmentButtonsBinding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doWithTransitionOnParentView

class ButtonsFragment : SimpleFragment<FragmentButtonsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentButtonsBinding::inflate,
    )

    companion object {
        fun newInstance() = ButtonsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        doWithTransitionOnParentView {
            binding.viewsScrollView.updatePadding(bottom = when (isKeyboardVisible) {
                true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            })
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Buttons"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }
    }
}