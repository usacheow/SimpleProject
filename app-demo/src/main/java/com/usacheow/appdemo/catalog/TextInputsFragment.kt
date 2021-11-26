package com.usacheow.appdemo.catalog

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentTextInputsBinding
import com.usacheow.core.date.DateTimeFormat
import com.usacheow.core.date.LocalDateFactory
import com.usacheow.core.date.LocalDateTimeFactory
import com.usacheow.core.date.toDateTimeFormat
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.addCurrencyFormatter
import com.usacheow.coreui.uikit.helper.addPhoneNumberFormatter
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.doOnActionClick
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class TextInputsFragment : SimpleFragment<FragmentTextInputsBinding>() {

    @Inject
    lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentTextInputsBinding::inflate,
    )

    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker().build().apply {
            addOnPositiveButtonClickListener {
                binding.viewDateInputHeaderView.text = it
                    ?.let { LocalDateTimeFactory.from(it) }
                    ?.format(DateTimeFormat.ISO_LOCAL_DATE.toDateTimeFormat())
            }
        }
    }
    private val dateRangePicker by lazy {
        MaterialDatePicker.Builder.dateRangePicker().build().apply {
            addOnPositiveButtonClickListener {
                val firstDate = it?.first
                    ?.let { LocalDateTimeFactory.from(it) }
                    ?.format(DateTimeFormat.ISO_LOCAL_DATE.toDateTimeFormat())
                val secondDate = it?.second
                    ?.let { LocalDateTimeFactory.from(it) }
                    ?.format(DateTimeFormat.ISO_LOCAL_DATE.toDateTimeFormat())
                binding.viewDateRangeInputHeaderView.text = "%s - %s".format(firstDate, secondDate)
            }
        }
    }
    private val timePicker by lazy {
        MaterialTimePicker.Builder().build().apply {
            addOnPositiveButtonClickListener {
                binding.viewTimeInputHeaderView.text = "%d:%d".format(hour, minute)
            }
        }
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.viewsScrollView.applyBottomInset(insets.getBottomInset(needIme = true))
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

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
        binding.viewDateInputHeaderView.doOnClick {
            showDatePicker()
        }
        binding.viewDateRangeInputHeaderView.doOnClick {
            showDateRangePicker()
        }
        binding.viewTimeInputHeaderView.doOnClick {
            showTimePicker()
        }
    }

    private fun showDatePicker() {
        datePicker.show(childFragmentManager, null)
    }

    private fun showDateRangePicker() {
        dateRangePicker.show(childFragmentManager, null)
    }

    private fun showTimePicker() {
        timePicker.show(childFragmentManager, null)
    }
}