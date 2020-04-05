package com.usacheow.coreuikit.demo

import android.os.Bundle
import android.view.WindowInsets
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.adapters.ViewTypesAdapter
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.utils.ext.PaddingValue
import com.usacheow.coreuikit.utils.ext.TODAY
import com.usacheow.coreuikit.widgets.ActionIconSize
import com.usacheow.coreuikit.widgets.ActionItem
import com.usacheow.coreuikit.widgets.ActionSelectionType
import com.usacheow.coreuikit.widgets.DividerItem
import com.usacheow.coreuikit.widgets.HeaderWithActionItem
import com.usacheow.coreuikit.widgets.OperationItem
import com.usacheow.coreuikit.widgets.SimpleButtonItem
import com.usacheow.coreuikit.widgets.SmallInfoCardItem
import com.usacheow.coreuikit.widgets.calendar.CalendarItem
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_widgets.widgetsListView

class WidgetsFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_widgets

    companion object {
        fun newInstance() = WidgetsFragment()
    }

    override fun inject(diProvider: DiProvider) {
    }

    override fun onApplyWindowInsets(insets: WindowInsets, padding: PaddingValue) {
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
                imageResId = R.drawable.ic_fingerprint,
                imageSize = ActionIconSize.SMALL,
                title = "Title"
            ),
            ActionItem(
                imageResId = R.drawable.ic_fingerprint,
                imageSize = ActionIconSize.SMALL,
                title = "Title",
                selectionType = ActionSelectionType.CHECK_BOX,
                onItemClicked = {}
            ),
            ActionItem(
                imageResId = R.drawable.ic_fingerprint,
                imageSize = ActionIconSize.BIG,
                title = "Title",
                isDraggable = true
            ),
            ActionItem(
                imageResId = R.drawable.ic_fingerprint,
                imageSize = ActionIconSize.SMALL,
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

            DividerItem.getBigDivider(),
            HeaderWithActionItem("Calendar", "Details"),

            CalendarItem(selectedMonth = TODAY(), isScrollable = true),

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