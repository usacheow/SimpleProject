package com.usacheow.featureotp

import android.os.Bundle
import android.text.InputFilter
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.usacheow.baseotp.OtpFeatureConnector
import com.usacheow.coreui.screen.SimpleModalFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.doOnClick
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.helper.makeGone
import com.usacheow.coreui.uikit.helper.makeVisible
import com.usacheow.coreui.uikit.helper.populate
import com.usacheow.coreui.uikit.organism.NumPadView
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.featureotp.databinding.FragmentSmsCodeBinding
import dagger.hilt.android.AndroidEntryPoint
import com.usacheow.coreui.R as CoreUiR

private const val HINT_CHAR = "0"

@AndroidEntryPoint
class SmsCodeModalFragment : SimpleModalFragment<FragmentSmsCodeBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentSmsCodeBinding::inflate,
    )

    private val viewModel by viewModels<SmsCodeViewModel>()
    private val otpStateViewModel by viewModels<OtpFeatureConnector>({ requireParentFragment() })

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.scrollView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiR.drawable.ic_close, action = ::dismiss)

        binding.smsCodeNumPadView.apply {
            setActionMode(NumPadView.ActionMode.NONE)
            onBackspaceClickedAction = { viewModel.onDigitRemoved() }
            onNumberClickedAction = { viewModel.onDigitAdded(it) }
        }
        binding.smsCodeResendButton.doOnClick { viewModel.onResendClicked() }
    }

    override fun subscribe() {
        viewModel.maxCodeLengthState.observe(viewLifecycleOwner) {
            binding.smsCodeInput.filters = arrayOf(InputFilter.LengthFilter(it))
            binding.smsCodeInput.hint = HINT_CHAR.repeat(it)
        }
        viewModel.inputtedCodeState.observe(viewLifecycleOwner) {
            binding.smsCodeInput.setText(it)
            binding.smsCodeNumPadView.setBackspaceButtonsVisibility(it.isNotEmpty())
        }
        viewModel.isResendButtonState.observe(viewLifecycleOwner) {
            binding.smsCodeResendButton.text = it.text
            binding.smsCodeResendButton.isEnabled = it.isEnable
        }
        viewModel.updateCodeStateAction.observe(viewLifecycleOwner) {
            when (it) {
                is SmsCodeState.CodeInputted -> {
                    binding.loaderView.makeVisible()
                    otpStateViewModel.onCodeInputted(it.code)
                }
                is SmsCodeState.CodeRequested -> {
                    otpStateViewModel.onResendClicked()
                }
            }
        }
        otpStateViewModel.featureEffect.observe(viewLifecycleOwner) {
            binding.loaderView.makeGone()
            when (it) {
                OtpFeatureConnector.Effect.Success -> dismiss()
                is OtpFeatureConnector.Effect.Error -> binding.smsCodeMessageView.populate(it.message)
            }
        }
    }
}