package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ActionSelectionType
import com.usacheow.coreui.uikit.molecule.ActionTileItem
import com.usacheow.coreui.utils.ImageSource
import com.usacheow.coreui.utils.TextSource
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class ActionTilesFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = ActionTilesFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.widgetsListView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Action tiles"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(
            listOf(
                ActionTileItem(
                    title = TextSource.Simple("Title"),
                ),

                ActionTileItem(
                    title = TextSource.Simple("Title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    selectionType = ActionSelectionType.SWITCH,
                ),

                ActionTileItem(
                    image = ImageSource.Res(R.drawable.demo_avatar),
                    title = TextSource.Simple("Title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    selectionType = ActionSelectionType.CHECK_BOX,
                    clickListener = {},
                ),

                ActionTileItem(
                    image = ImageSource.Res(R.drawable.demo_avatar),
                    title = TextSource.Simple("Title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    selectionType = ActionSelectionType.SWITCH,
                    isChecked = true,
                    clickListener = {},
                ),

                ActionTileItem.shimmer(),
            )
        )
    }
}