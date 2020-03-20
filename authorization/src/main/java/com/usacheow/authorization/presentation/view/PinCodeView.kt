package com.usacheow.authorization.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import com.usacheow.authorization.R
import kotlinx.android.synthetic.main.view_pin_code.view.hintTextView
import kotlinx.android.synthetic.main.view_pin_code.view.indicatorView
import kotlinx.android.synthetic.main.view_pin_code.view.numPadView

private const val PIN_CODE_LENGTH = 4

class PinCodeView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : GridLayout(context, attrs, defStyleAttr) {

    var onBiometricButtonClickedAction: (() -> Unit)? = null
    var onCodeEnteredAction: ((String) -> Unit)? = null

    private val pinCode = StringBuilder()
    private var isErrorState = false

    init {
        inflate(context, R.layout.view_pin_code, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        numPadView.onNumberClickedAction = ::onNumberButtonClicked
        numPadView.onBackspaceClickedAction = ::onBackspaceClicked
        numPadView.onBiometricClickedAction = onBiometricButtonClickedAction
    }

    private fun onNumberButtonClicked(value: CharSequence?) {
        if (isErrorState) {
            isErrorState = false
            pinCode.clear()
            indicatorView.refreshPinView()
        }

        if (pinCode.length < PIN_CODE_LENGTH) {
            pinCode.append(value)
            indicatorView.increaseCounter()
        }

        checkBackspaceButtonsState()

        if (pinCode.length == PIN_CODE_LENGTH) {
            onCodeEnteredAction?.invoke(pinCode.toString())
        }
    }

    private fun checkBackspaceButtonsState() {
        numPadView.setBackspaceButtonsVisibility(pinCode.isNotEmpty())
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
        indicatorView.refreshPinView()
    }

    private fun deleteLastNumeral() {
        pinCode.deleteCharAt(pinCode.length - 1)
        indicatorView.decreaseCounter()
        checkBackspaceButtonsState()
    }

    fun showError() {
        isErrorState = true
        indicatorView.indicateError()
    }

    fun setHint(hint: String) {
        hintTextView.text = hint
    }

    fun setFingerprintEnabled(isEnabled: Boolean) {
        numPadView.isFingerprintEnabled = isEnabled
    }
}