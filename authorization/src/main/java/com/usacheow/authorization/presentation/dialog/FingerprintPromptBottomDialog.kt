package com.usacheow.authorization.presentation.dialog

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usacheow.authorization.R
import com.usacheow.coreuikit.utils.ext.doOnClick
import kotlinx.android.synthetic.main.bottom_dialog_fingerprint_prompt.fingerprintCancelButton
import kotlinx.android.synthetic.main.bottom_dialog_fingerprint_prompt.fingerprintIconView
import kotlinx.android.synthetic.main.bottom_dialog_fingerprint_prompt.fingerprintMessageView

class FingerprintPromptBottomDialog(context: Context) : BottomSheetDialog(context) {

    init {
        setContentView(layoutInflater.inflate(R.layout.bottom_dialog_fingerprint_prompt, null))
        fingerprintCancelButton.doOnClick { dismiss() }
        showNormalState()
    }

    fun showErrorState() {
        val color = ContextCompat.getColor(context, R.color.error)
        DrawableCompat.setTint(fingerprintIconView.drawable, color)
        fingerprintMessageView.setTextColor(color)
        fingerprintMessageView.text = context.resources.getString(R.string.fingerprint_message_error)
    }

    fun showSuccessState() {
        val color = ContextCompat.getColor(context, R.color.success)
        DrawableCompat.setTint(fingerprintIconView.drawable, color)
        fingerprintMessageView.setTextColor(color)
        fingerprintMessageView.text = context.resources.getString(R.string.fingerprint_message_success)
    }

    private fun showNormalState() {
        val color = ContextCompat.getColor(context, R.color.colorIconPrimary)
        DrawableCompat.setTint(fingerprintIconView.drawable, color)
        fingerprintMessageView.setTextColor(color)
        fingerprintMessageView.text = context.resources.getString(R.string.fingerprint_message)
    }
}