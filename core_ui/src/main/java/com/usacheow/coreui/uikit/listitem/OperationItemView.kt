package com.usacheow.coreui.uikit.listitem

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.usacheow.coreui.R
import com.usacheow.coreui.adapters.base.Populatable
import com.usacheow.coreui.adapters.base.ViewType
import com.usacheow.coreui.databinding.ViewOperationItemBinding
import com.usacheow.coreui.utils.view.load
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.setListenerIfNeed

class OperationItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Populatable<OperationItem> {

    private val binding by lazy { ViewOperationItemBinding.bind(this) }

    override fun populate(model: OperationItem) {
        model.imageUrl?.let {
            binding.operationImageView.load(model.imageUrl)
        } ?: binding.operationImageView.makeGone()

        binding.operationCardNumberView.text = model.cardNumber
        binding.operationTypeView.text = model.operationType
        binding.operationNameView.text = model.name
        binding.operationSumView.text = model.sum

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