package com.usacheow.appstate.otp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputFilter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.usacheow.appstate.R
import com.usacheow.appstate.databinding.FragmentSmsCodeBinding
import com.usacheow.coreui.fragment.SimpleModalFragment
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.doOnClick
import dagger.hilt.android.AndroidEntryPoint

private const val CODE_LENGTH_KEY = "CODE_LENGTH_KEY"
private const val CODE_LENGTH_DEFAULT_VALUE = 4

@AndroidEntryPoint
class SmsCodeModalFragment : SimpleModalFragment<FragmentSmsCodeBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentSmsCodeBinding::inflate,
    )

    private val viewModel by viewModels<SmsCodeViewModel>({ requireParentFragment() })

    companion object {
        fun newInstance(codeLength: Int) = SmsCodeModalFragment().apply {
            arguments = bundleOf(CODE_LENGTH_KEY to codeLength)
        }
    }

    override fun processArguments(bundle: Bundle?) {
        val maxCodeLength = arguments?.getInt(CODE_LENGTH_KEY) ?: CODE_LENGTH_DEFAULT_VALUE
        viewModel.setupInitState(maxCodeLength)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.smsCodeNumPadView.apply {
            isFingerprintEnabled = false
            onBackspaceClickedAction = { viewModel.onDigitRemoved() }
            onNumberClickedAction = { viewModel.onDigitAdded(it) }
        }
        binding.smsCodeCloseButton.doOnClick { dismiss() }
        binding.smsCodeResendButton.doOnClick { viewModel.onResendClicked() }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.onDismissed()
    }

    override fun subscribe() {
        viewModel.isLoadingState.observe(lifecycle) { binding.loaderView.root.isVisible = it }
        viewModel.maxCodeLengthState.observe(lifecycle) {
            binding.smsCodeInput.filters = arrayOf(InputFilter.LengthFilter(it))
        }
        viewModel.inputtedCodeState.observe(lifecycle) {
            binding.smsCodeInput.setText(it)
            binding.smsCodeNumPadView.setBackspaceButtonsVisibility(it.isNotEmpty())
        }
        viewModel.showMessageAction.observe(lifecycle) { binding.smsCodeMessageView.text = it }
        viewModel.closeScreenAction.observe(lifecycle) { dismiss() }
        viewModel.resendButtonTextState.observe(lifecycle) { binding.smsCodeResendButton.text = it }
        viewModel.isResendButtonEnabledAction.observe(lifecycle) {
            binding.smsCodeResendButton.setEnabledTextButton(it)
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