package com.usacheow.featureauth.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import com.usacheow.coreui.utils.view.string
import com.usacheow.featureauth.R
import com.usacheow.featureauth.databinding.ViewPinCodeBinding

private const val PIN_CODE_LENGTH = 4

class PinCodeView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : GridLayout(context, attrs, defStyleAttr) {

    var onBiometricButtonClickedAction: (() -> Unit)? = null
    var onCodeEnteredAction: ((String) -> Unit)? = null

    private val pinCode = StringBuilder()
    private var isErrorState = false

    private var binding = ViewPinCodeBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding.numPadView.onNumberClickedAction = ::onNumberButtonClicked
        binding.numPadView.onBackspaceClickedAction = ::onBackspaceClicked
        binding.numPadView.onBiometricClickedAction = { onBiometricButtonClickedAction?.invoke() }
    }

    private fun onNumberButtonClicked(value: CharSequence?) {
        if (isErrorState) {
            isErrorState = false
            pinCode.clear()
            binding.indicatorView.refreshPinView()
        }

        if (pinCode.length < PIN_CODE_LENGTH) {
            pinCode.append(value)
            binding.indicatorView.increaseCounter()
        }

        checkBackspaceButtonsState()

        if (pinCode.length == PIN_CODE_LENGTH) {
            onCodeEnteredAction?.invoke(pinCode.toString())
        }
    }

    private fun checkBackspaceButtonsState() {
        binding.numPadView.setBackspaceButtonsVisibility(pinCode.isNotEmpty())
    }

    private fun onBackspaceClicked() {
        if (isErrorState) {
            clearCode()
        } else if (pinCode.isNotEmpty()) {
            deleteLastNumeral()
        }
    }

    private fun clearCode() {
        isErrorState = false
        pinCode.clear()
        checkBackspaceButtonsState()
        binding.indicatorView.refreshPinView()
    }

    private fun deleteLastNumeral() {
        pinCode.deleteCharAt(pinCode.length - 1)
        binding.indicatorView.decreaseCounter()
        checkBackspaceButtonsState()
    }

    fun showError() {
        isErrorState = true
        binding.indicatorView.indicateError()
    }

    fun setHint(hint: String) {
        binding.hintTextView.text = hint
    }

    fun resetState() {
        clearCode()
        binding.hintTextView.text = string(R.string.pin_view_hint)
    }

    fun setFingerprintEnabled(isEnabled: Boolean) {
        binding.numPadView.isFingerprintEnabled = isEnabled
    }
}