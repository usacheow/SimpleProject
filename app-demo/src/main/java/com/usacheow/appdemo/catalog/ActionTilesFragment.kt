package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.molecule.ActionSelectionType
import com.usacheow.coreui.uikit.molecule.ActionTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class ActionTilesFragment : SimpleFragment<FragmentListBinding>() {

    @Inject lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.widgetsListView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.title = "Action tiles"
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewStateAdapter(
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
                    image = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    title = TextSource.Simple("Title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    selectionType = ActionSelectionType.CHECK_BOX,
                    clickListener = {},
                ),

                ActionTileItem(
                    image = ImageSource.Res(DemoAppR.drawable.demo_avatar),
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