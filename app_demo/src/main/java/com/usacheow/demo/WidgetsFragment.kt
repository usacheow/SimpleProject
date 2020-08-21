package com.usacheow.demo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.button.SimpleButtonItem
import com.usacheow.coreui.uikit.decoration.DividerItem
import com.usacheow.coreui.uikit.header.HeaderWithActionItem
import com.usacheow.coreui.uikit.information.SmallInfoCardItem
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.uikit.listitem.ActionSelectionType
import com.usacheow.coreui.uikit.listitem.OperationItem
import com.usacheow.coreui.uikit.utils.IconState
import com.usacheow.coreui.utils.view.PaddingValue
import kotlinx.android.synthetic.main.fragment_widgets.widgetsListView

class WidgetsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_widgets

    companion object {
        fun newInstance() = WidgetsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        widgetsListView.updatePadding(
            top = insets.systemWindowInsetTop,
            bottom = insets.systemWindowInsetBottom
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        widgetsListView.layoutManager = LinearLayoutManager(context)
        widgetsListView.adapter = ViewTypesAdapter(listOf(
            HeaderWithActionItem("Action items", "Details"),

            ActionItem(
                title = "Title",
                subtitle = "Subtitle"
            ),
            ActionItem(
                imageInfo = IconState(resId = R.drawable.ic_fingerprint),
                title = "Title"
            ),
            ActionItem(
                imageInfo = IconState(resId = R.drawable.ic_fingerprint),
                title = "Title",
                selectionType = ActionSelectionType.CHECK_BOX,
                onItemClicked = {}
            ),
            ActionItem(
                imageInfo = IconState(resId = R.drawable.ic_fingerprint),
                title = "Title",
                subtitle = "Subtitle",
                selectionType = ActionSelectionType.CHECK_BOX,
                isChecked = true,
                onControlClicked = {}
            ),

            DividerItem.getBigDivider(),
            HeaderWithActionItem("Operation item", "Details"),

            OperationItem(
                operationType = "Some operation",
                cardNumber = "*1234",
                name = "Operation name",
                sum = "500.00 ₽"
            ),

//            DividerItem.getBigDivider(),
//            HeaderWithActionItem("Calendar", "Details"),
//
//            CalendarItem(selectedMonth = TODAY(), isScrollable = true),

            DividerItem.getBigDivider(),
            HeaderWithActionItem("Small info card item", "Details"),

            SmallInfoCardItem(header = "Header", value = "Some value text", needExpandOnWidth = true),
            SmallInfoCardItem(header = "Header", value = "Some value text", needExpandOnWidth = false),

            DividerItem.getBigDivider(),
            HeaderWithActionItem("Simple button", "Details"),

            SimpleButtonItem(text = "Button") {},

            DividerItem.getBigDivider()
        ))
    }
}