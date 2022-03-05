package com.usacheow.coreuiview.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import com.google.android.material.button.MaterialButton
import com.usacheow.coreuiview.tools.Populatable
import com.usacheow.coreuiview.tools.ViewState
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.resource.toPx
import com.usacheow.coreuiview.R as CoreUiViewR

class SimpleTextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialButton(context, attrs), Populatable<SimpleTextButtonItem> {

    override fun populate(model: SimpleTextButtonItem) {
        text = model.text
        doOnClick { model.clickListener() }

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
    val clickListener: () -> Unit,
) : ViewState(CoreUiViewR.layout.view_simple_text_button_tile)