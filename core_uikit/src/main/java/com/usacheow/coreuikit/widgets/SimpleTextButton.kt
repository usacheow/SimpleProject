package com.usacheow.coreuikit.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import com.google.android.material.button.MaterialButton
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.base.Populatable
import com.usacheow.coreuikit.base.ViewType
import com.usacheow.coreuikit.utils.ext.doOnClick
import com.usacheow.coreuikit.utils.ext.toPx

class SimpleTextButton
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : MaterialButton(context, attrs, defStyleAttr), Populatable<SimpleTextButtonItem> {

    override fun populate(model: SimpleTextButtonItem) {
        text = model.text
        doOnClick { model.clickAction() }

        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMargins(
                top = model.verticalMarginDp.toPx,
                bottom = model.verticalMarginDp.toPx,
                right = model.horizontalMarginDp.toPx,
                left = model.horizontalMarginDp.toPx
            )
        }
    }
}

data class SimpleTextButtonItem(
    val text: String,
    val verticalMarginDp: Int = 4,
    val horizontalMarginDp: Int = 16,
    val clickAction: () -> Unit
) : ViewType(R.layout.simple_text_button)