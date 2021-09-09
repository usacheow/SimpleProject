package com.usacheow.featureotp

import android.os.Bundle
import android.text.InputFilter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.usacheow.baseotp.OtpFeatureConnector
import com.usacheow.coreui.fragment.SimpleModalFragment
import com.usacheow.coreui.uikit.organism.NumPadView
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.populate
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.makeVisible
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.featureotp.databinding.FragmentSmsCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmsCodeModalFragment : SimpleModalFragment<FragmentSmsCodeBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentSmsCodeBinding::inflate,
    )

    private val viewModel by viewModels<SmsCodeViewModel>()
    private val otpStateViewModel by viewModels<OtpFeatureConnector>({ requireParentFragment() })

    companion object {
        fun bundle(codeLength: Int) = bundleOf(SmsCodeViewModel.CODE_LENGTH_KEY to codeLength)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.smsCodeNumPadView.apply {
            setActionMode(NumPadView.ActionMode.NONE)
            onBackspaceClickedAction = { viewModel.onDigitRemoved() }
            onNumberClickedAction = { viewModel.onDigitAdded(it) }
        }
        binding.smsCodeCloseButton.doOnClick { dismiss() }
        binding.smsCodeResendButton.doOnClick { viewModel.onResendClicked() }
    }

    override fun subscribe() {
        viewModel.maxCodeLengthState.observe(viewLifecycleOwner) {
            binding.smsCodeInput.filters = arrayOf(InputFilter.LengthFilter(it))
        }
        viewModel.inputtedCodeState.observe(viewLifecycleOwner) {
            binding.smsCodeInput.setText(it)
            binding.smsCodeNumPadView.setBackspaceButtonsVisibility(it.isNotEmpty())
        }
        viewModel.isResendButtonState.observe(viewLifecycleOwner) {
            binding.smsCodeResendButton.text = it.text
            binding.smsCodeResendButton.setEnabledTextButton(it.isEnable)
        }
        viewModel.updateCodeStateAction.observe(viewLifecycleOwner) {
            when (it) {
                is SmsCodeState.CodeInputted -> {
                    binding.loaderView.root.makeVisible()
                    otpStateViewModel.onCodeInputted(it.code)
                }
                is SmsCodeState.CodeRequested -> {
                    otpStateViewModel.onResendClicked()
                }
            }
        }
        otpStateViewModel.closeDialogAction.observe(viewLifecycleOwner) {
            binding.loaderView.root.makeGone()
            dismiss()
        }
        otpStateViewModel.showErrorMessageAction.observe(viewLifecycleOwner) {
            binding.loaderView.root.makeGone()
            binding.smsCodeMessageView.populate(it)
        }
    }

    private fun TextView.setEnabledTextButton(isEnabled: Boolean) {
        this.isEnabled = isEnabled
        setTextColor(
            when (isEnabled) {
                true -> R.color.colorPrimary
                false -> R.color.disabled
            }
        )
    }
}