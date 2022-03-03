package com.usacheow.coreuiview.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import androidx.annotation.DrawableRes
import androidx.core.view.isInvisible
import com.usacheow.coreuiview.databinding.ViewNumPadBinding
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.doOnImmediateClick
import com.usacheow.coreuiview.R as CoreUiViewR
import com.usacheow.coreuitheme.R as CoreUiThemeR

class NumPadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : GridLayout(context, attrs) {

    var onBackspaceClickedAction: (() -> Unit)? = null
    var onActionClickedAction: (() -> Unit)? = null
    var onNumberClickedAction: ((String) -> Unit)? = null

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
            button.doOnImmediateClick { onNumberClickedAction?.invoke(button.text.toString()) }
        }
        binding.actionButton.doOnClick { onActionClickedAction?.invoke() }
        binding.backspaceButton.doOnImmediateClick { onBackspaceClickedAction?.invoke() }
    }

    fun setBackspaceButtonsVisibility(isVisible: Boolean) {
        binding.backspaceButton.isInvisible = !isVisible
    }

    fun setActionMode(mode: ActionMode) {
        binding.actionButton.isInvisible = mode == ActionMode.NONE
        if (mode != ActionMode.NONE) {
            binding.actionButton.setImageResource(mode.iconRes)
        }
    }

    enum class ActionMode(@DrawableRes val iconRes: Int) {
        BIOMETRIC(CoreUiThemeR.drawable.ic_fingerprint),
        NEXT(CoreUiThemeR.drawable.ic_next),
        ACCEPT(CoreUiThemeR.drawable.ic_accept),
        NONE(-1),
    }
}