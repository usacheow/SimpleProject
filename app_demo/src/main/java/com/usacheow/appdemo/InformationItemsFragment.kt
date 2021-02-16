package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.atom.DividerItem
import com.usacheow.coreui.uikit.atom.SpaceItem
import com.usacheow.coreui.uikit.molecule.HeaderWithActionItem
import com.usacheow.coreui.uikit.molecule.OperationItem
import com.usacheow.coreui.uikit.molecule.SmallInfoCardItem
import com.usacheow.coreui.utils.view.PaddingValue

class InformationItemsFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = InformationItemsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Information items"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            HeaderWithActionItem("Header item", "With action"),

            DividerItem.getSmallDivider(),

            OperationItem(
                operationType = "Operation type",
                cardNumber = "*1234",
                name = "Operation name",
                sum = "500.00 ₽"
            ),

            DividerItem.getSmallDivider(),

            SmallInfoCardItem(header = "Small info card", value = "Width = match_parent", needExpandOnWidth = true),

            SpaceItem(heightDp = 16),

            SmallInfoCardItem(header = "Clickable small info card", value = "Width = wrap_content", needExpandOnWidth = false, clickAction = {}),

            SpaceItem(heightDp = 16),

            SmallInfoCardItem.shimmer(),
        ))
    }
}