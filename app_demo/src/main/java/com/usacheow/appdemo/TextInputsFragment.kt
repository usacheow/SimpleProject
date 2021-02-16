package com.usacheow.appdemo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.databinding.FragmentTextInputsBinding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doWithTransitionOnParentView
import com.usacheow.coreui.utils.textinput.addCurrencyFormatter
import com.usacheow.coreui.utils.textinput.addPhoneNumberFormatter

class TextInputsFragment : SimpleFragment<FragmentTextInputsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentTextInputsBinding::inflate,
    )

    companion object {
        fun newInstance() = TextInputsFragment()
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
            title = "Text inputs"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.viewAmountInput.addCurrencyFormatter("50000.00")
        binding.viewPhoneNumberInput.addPhoneNumberFormatter({}, {})

        binding.viewPasswordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.viewAmountInput.requestFocus()
            }
            false
        }
        binding.viewAmountInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.viewPhoneNumberInput.requestFocus()
            }
            false
        }
        binding.viewPhoneNumberInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.viewPhoneNumberInput.clearFocus()
            }
            false
        }
    }
}