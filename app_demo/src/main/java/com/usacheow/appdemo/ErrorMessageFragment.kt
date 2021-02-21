package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.databinding.FragmentErrorMessageBinding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.organism.ErrorMessageItem
import com.usacheow.coreui.uikit.organism.showOrHideError
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue

class ErrorMessageFragment : SimpleFragment<FragmentErrorMessageBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentErrorMessageBinding::inflate,
    )

    companion object {
        fun newInstance() = ErrorMessageFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        binding.viewsScrollView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Error message view"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.errorMessageView.showOrHideError(ErrorMessageItem(
            title = TextString("Error title"),
            description = TextString("Error description"),
            repeatClickAction = {},
        ))
    }
}