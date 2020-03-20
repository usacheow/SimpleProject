package com.usacheow.coreuikit.views

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import androidx.core.view.isInvisible
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.utils.ext.doOnClick
import kotlinx.android.synthetic.main.view_num_pad.view.backspaceButton
import kotlinx.android.synthetic.main.view_num_pad.view.fingerprintButton
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton0
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton1
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton2
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton3
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton4
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton5
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton6
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton7
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton8
import kotlinx.android.synthetic.main.view_num_pad.view.numPadButton9

class NumPadView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : GridLayout(context, attrs, defStyleAttr) {

    var onBackspaceClickedAction: (() -> Unit)? = null
    var onBiometricClickedAction: (() -> Unit)? = null
    var onNumberClickedAction: ((String) -> Unit)? = null
    var isFingerprintEnabled = false
        set(value) {
            field = value
            fingerprintButton.isInvisible = !value
        }

    private val numberButtons by lazy {
        arrayOf(
            numPadButton1,
            numPadButton2,
            numPadButton3,
            numPadButton4,
            numPadButton5,
            numPadButton6,
            numPadButton7,
            numPadButton8,
            numPadButton9,
            numPadButton0
        )
    }

    init {
        inflate(context, R.layout.view_num_pad, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberClickedAction?.invoke(button.text.toString()) }
        }
        fingerprintButton.doOnClick { onBiometricClickedAction?.invoke() }
        backspaceButton.setOnClickListener { onBackspaceClickedAction?.invoke() }
    }

    fun setBackspaceButtonsVisibility(isVisible: Boolean) {
        backspaceButton.isInvisible = !isVisible
    }
}