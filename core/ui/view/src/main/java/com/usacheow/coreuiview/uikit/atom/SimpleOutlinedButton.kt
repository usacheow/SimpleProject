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

class SimpleOutlinedButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : MaterialButton(context, attrs), Populatable<SimpleOutlinedButtonItem> {

    override fun populate(model: SimpleOutlinedButtonItem) {
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

data class SimpleOutlinedButtonItem(
    val text: String,
    val verticalMarginDp: Int = 4,
    val horizontalMarginDp: Int = 16,
    val clickListener: () -> Unit,
) : ViewState(CoreUiViewR.layout.view_simple_outlined_button_tile)