package com.usacheow.demo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.list.Filter
import com.usacheow.coreui.utils.textinput.addCurrencyFormatter
import com.usacheow.coreui.utils.textinput.addPhoneNumberFormatter
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doWithTransitionOnParentView
import com.usacheow.demo.databinding.FragmentViewsBinding

class ViewsFragment : SimpleFragment<FragmentViewsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentViewsBinding::inflate,
    )

    companion object {
        fun newInstance() = ViewsFragment()
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
            title = "Views Samples"
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

        binding.chipsLayout.populate(setOf(
            Filter(1, "Chip 1", true),
            Filter(2, "Chip 2", false),
            Filter(3, "Chip 3", false),
            Filter(4, "Chip 4", false),
            Filter(5, "Chip 5", false)
        )) { _, _ -> }
    }
}