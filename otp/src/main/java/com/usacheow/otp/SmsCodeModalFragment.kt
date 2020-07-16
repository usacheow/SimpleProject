package com.usacheow.otp

import android.app.Application
import android.os.Bundle
import android.text.InputFilter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.usacheow.coreui.fragments.SimpleModalFragment
import com.usacheow.coreui.livedata.subscribe
import com.usacheow.coreui.utils.ext.doOnClick
import kotlinx.android.synthetic.main.fragment_sms_code.loaderView
import kotlinx.android.synthetic.main.fragment_sms_code.smsCodeCloseButton
import kotlinx.android.synthetic.main.fragment_sms_code.smsCodeInput
import kotlinx.android.synthetic.main.fragment_sms_code.smsCodeMessageView
import kotlinx.android.synthetic.main.fragment_sms_code.smsCodeNumPadView
import kotlinx.android.synthetic.main.fragment_sms_code.smsCodeResendButton

private const val CODE_LENGTH_KEY = "CODE_LENGTH_KEY"
private const val CODE_LENGTH_DEFAULT_VALUE = 4

class SmsCodeModalFragment : SimpleModalFragment() {

    override val layoutId = R.layout.fragment_sms_code

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SmsCodeViewModel>({ requireParentFragment() }, { viewModelFactory })

    companion object {
        fun newInstance(codeLength: Int) = SmsCodeModalFragment().apply {
            arguments = bundleOf(CODE_LENGTH_KEY to codeLength)
        }
    }

    override fun inject(application: Application) = Unit

    override fun processArguments(bundle: Bundle?) {
        val maxCodeLength = arguments?.getInt(CODE_LENGTH_KEY) ?: CODE_LENGTH_DEFAULT_VALUE
        smsCodeInput.filters = arrayOf(InputFilter.LengthFilter(maxCodeLength))
        viewModel.setupInitState(maxCodeLength)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        smsCodeNumPadView.apply {
            isFingerprintEnabled = false
            onBackspaceClickedAction = { viewModel.onDigitRemoved() }
            onNumberClickedAction = { viewModel.onDigitAdded(it) }
        }
        smsCodeCloseButton.doOnClick { dismiss() }
        smsCodeResendButton.doOnClick { viewModel.onResendClicked() }
    }

    override fun subscribe() {
        viewModel.isLoadingState.subscribe(viewLifecycleOwner) { loaderView.isVisible = it }
        viewModel.inputtedCode.subscribe(viewLifecycleOwner) {
            smsCodeInput.setText(it)
            smsCodeNumPadView.setBackspaceButtonsVisibility(it.isNotEmpty())
        }
        viewModel.resendButtonText.subscribe(viewLifecycleOwner) { smsCodeResendButton.text = it }
        viewModel.isResendButtonEnabled.subscribe(viewLifecycleOwner) { smsCodeResendButton.setEnabledTextButton(it) }
        viewModel.showMessage.subscribe(viewLifecycleOwner) { smsCodeMessageView.text = it }
        viewModel.closeScreen.subscribe(viewLifecycleOwner) { dismiss() }
    }

    private fun TextView.setEnabledTextButton(isEnabled: Boolean) {
        this.isEnabled = isEnabled
        setTextColor(when (isEnabled) {
            true -> R.color.colorPrimary
            false -> R.color.disabled
        })
    }
}