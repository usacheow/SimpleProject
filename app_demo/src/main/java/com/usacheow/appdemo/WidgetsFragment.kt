package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.button.SimpleButtonItem
import com.usacheow.coreui.uikit.button.SimpleOutlinedButtonItem
import com.usacheow.coreui.uikit.button.SimpleTextButtonItem
import com.usacheow.coreui.uikit.decoration.DividerItem
import com.usacheow.coreui.uikit.header.HeaderWithActionItem
import com.usacheow.coreui.uikit.information.SmallInfoCardItem
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.uikit.listitem.ActionSelectionType
import com.usacheow.coreui.uikit.listitem.OperationItem
import com.usacheow.coreui.uikit.utils.IconState
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.appdemo.databinding.FragmentWidgetsBinding

class WidgetsFragment : SimpleFragment<FragmentWidgetsBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentWidgetsBinding::inflate,
    )

    companion object {
        fun newInstance() = WidgetsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Widgets samples"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
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

            DividerItem(heightResId = R.dimen.divider_height_big, colorResId = R.color.colorBackground),

            OperationItem(
                operationType = "Some operation",
                cardNumber = "*1234",
                name = "Operation name",
                sum = "500.00 ₽"
            ),

            DividerItem.getSmallDivider(),
            HeaderWithActionItem("Buttons"),

            SimpleButtonItem(text = "Button") {},
            SimpleOutlinedButtonItem(text = "Outlined button") {},
            SimpleTextButtonItem(text = "Text button") {},

            SmallInfoCardItem(header = "Small info card", value = "Width = match_parent", needExpandOnWidth = true),
            SmallInfoCardItem(header = "Clickable small info card", value = "Width = wrap_content", needExpandOnWidth = false, clickAction = {}),

            DividerItem.getBigDivider(),
        ))
    }
}