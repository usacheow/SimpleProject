package com.usacheow.coreui.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import androidx.core.view.isInvisible
import com.usacheow.coreui.databinding.ViewNumPadBinding
import com.usacheow.coreui.utils.view.doOnClick

class NumPadView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : GridLayout(context, attrs, defStyleAttr) {

    var onBackspaceClickedAction: (() -> Unit)? = null
    var onBiometricClickedAction: (() -> Unit)? = null
    var onNumberClickedAction: ((String) -> Unit)? = null
    var isFingerprintEnabled = false
        set(value) {
            field = value
            binding.fingerprintButton.isInvisible = !value
        }

    private val numberButtons by lazy {
        arrayOf(
            binding.numPadButton1,
            binding.numPadButton2,
            binding.numPadButton3,
            binding.numPadButton4,
            binding.numPadButton5,
            binding.numPadButton6,
            binding.numPadButton7,
            binding.numPadButton8,
            binding.numPadButton9,
            binding.numPadButton0
        )
    }

    private val binding by lazy { ViewNumPadBinding.inflate(LayoutInflater.from(context), this, true) }

    override fun onFinishInflate() {
        super.onFinishInflate()
        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberClickedAction?.invoke(button.text.toString()) }
        }
        binding.fingerprintButton.doOnClick { onBiometricClickedAction?.invoke() }
        binding.backspaceButton.setOnClickListener { onBackspaceClickedAction?.invoke() }
    }

    fun setBackspaceButtonsVisibility(isVisible: Boolean) {
        binding.backspaceButton.isInvisible = !isVisible
    }
}