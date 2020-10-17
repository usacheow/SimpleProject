package com.usacheow.demo

import android.os.Bundle
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.uikit.list.Filter
import com.usacheow.coreui.utils.textinput.addCurrencyFormatter
import com.usacheow.coreui.utils.textinput.addPhoneNumberFormatter
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doWithTransitionOnParentView
import kotlinx.android.synthetic.main.fragment_views.*

class ViewsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_views

    companion object {
        fun newInstance() = ViewsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        doWithTransitionOnParentView {
            viewsScrollView.updatePadding(bottom = when (isKeyboardVisible) {
                true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            })
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            title = "Views Samples"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        viewAmountInput.addCurrencyFormatter("50000.00")
        viewPhoneNumberInput.addPhoneNumberFormatter({}, {})

        viewPasswordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewAmountInput.requestFocus()
            }
            false
        }
        viewAmountInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewPhoneNumberInput.requestFocus()
            }
            false
        }
        viewPhoneNumberInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewPhoneNumberInput.clearFocus()
            }
            false
        }

        chipsLayout.populate(setOf(
            Filter(1, "Chip 1", true),
            Filter(2, "Chip 2", false),
            Filter(3, "Chip 3", false),
            Filter(4, "Chip 4", false),
            Filter(5, "Chip 5", false)
        )) { _, _ -> }
    }
}