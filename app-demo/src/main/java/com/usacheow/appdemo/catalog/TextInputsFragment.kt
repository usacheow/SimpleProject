package com.usacheow.appdemo.catalog

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.databinding.FragmentTextInputsBinding
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.addCurrencyFormatter
import com.usacheow.coreui.uikit.helper.addPhoneNumberFormatter
import com.usacheow.coreui.uikit.helper.doOnActionClick
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.R as CoreUiR

class TextInputsFragment : SimpleFragment<FragmentTextInputsBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentTextInputsBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.viewsScrollView.updatePadding(bottom = insets.getBottomInset(needIme = true))
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Text inputs"
            setNavigationAction(CoreUiR.drawable.ic_back) {
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