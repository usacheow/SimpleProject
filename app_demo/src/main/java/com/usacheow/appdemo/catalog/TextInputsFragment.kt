package com.usacheow.appdemo.catalog

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentTextInputsBinding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.textinput.addCurrencyFormatter
import com.usacheow.coreui.utils.textinput.addPhoneNumberFormatter
import com.usacheow.coreui.utils.textinput.doOnActionClick
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class TextInputsFragment : SimpleFragment<FragmentTextInputsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentTextInputsBinding::inflate,
    )

    companion object {
        fun newInstance() = TextInputsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.viewsScrollView.updatePadding(bottom = insets.getBottomInset(needIme = true))
        return insets
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

        binding.viewPasswordInput.doOnActionClick(EditorInfo.IME_ACTION_NEXT) {
            binding.viewAmountInput.requestFocus()
        }
        binding.viewAmountInput.doOnActionClick(EditorInfo.IME_ACTION_NEXT) {
            binding.viewPhoneNumberInput.requestFocus()
        }
        binding.viewPhoneNumberInput.doOnActionClick(EditorInfo.IME_ACTION_DONE) {
            binding.viewPhoneNumberInput.clearFocus()
        }
    }
}