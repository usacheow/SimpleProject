package com.usacheow.coreui.uikit.listitem

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.utils.view.load
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.setListenerIfNeed
import kotlinx.android.synthetic.main.view_operation_item.view.operationCardNumberView
import kotlinx.android.synthetic.main.view_operation_item.view.operationImageView
import kotlinx.android.synthetic.main.view_operation_item.view.operationNameView
import kotlinx.android.synthetic.main.view_operation_item.view.operationSumView
import kotlinx.android.synthetic.main.view_operation_item.view.operationTypeView

class OperationItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<OperationItem> {

    override fun populate(model: OperationItem) {
        model.imageUrl?.let {
            operationImageView.load(model.imageUrl)
        } ?: operationImageView.makeGone()

        operationCardNumberView.text = model.cardNumber
        operationTypeView.text = model.operationType
        operationNameView.text = model.name
        operationSumView.text = model.sum

        setListenerIfNeed(model.onItemClicked)
    }
}

data class OperationItem(
    val imageUrl: String? = null,
    val operationType: String,
    val cardNumber: String,
    val name: String,
    val sum: String,
    val onItemClicked: (() -> Unit)? = null
) : ViewType(R.layout.view_operation_item)