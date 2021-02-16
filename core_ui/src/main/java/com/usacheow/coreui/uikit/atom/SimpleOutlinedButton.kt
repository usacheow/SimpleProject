package com.usacheow.coreui.uikit.atom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import com.google.android.material.button.MaterialButton
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.coreui.utils.view.toPx

class SimpleOutlinedButton
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : MaterialButton(context, attrs, defStyleAttr), Populatable<SimpleOutlinedButtonItem> {

    override fun populate(model: SimpleOutlinedButtonItem) {
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

data class SimpleOutlinedButtonItem(
    val text: String,
    val verticalMarginDp: Int = 4,
    val horizontalMarginDp: Int = 16,
    val clickAction: () -> Unit
) : ViewType(R.layout.view_simple_outlined_button_item)