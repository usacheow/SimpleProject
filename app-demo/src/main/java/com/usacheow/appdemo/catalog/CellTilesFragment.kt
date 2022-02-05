package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.core.resource.ImageSource
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.molecule.CellLeftPart
import com.usacheow.coreui.uikit.molecule.CellRightPart
import com.usacheow.coreui.uikit.molecule.CellTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class CellTilesFragment : SimpleFragment<FragmentListBinding>() {

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
        binding.header.title = "Cell tiles"
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewStateAdapter(
            listOf(

                CellTileItem(
                    title = TextSource.Simple("Cell tile title"),
                ),
                CellTileItem(
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                ),

                CellTileItem(
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    additional = TextSource.Simple("Additional text with some interesting information"),
                ),

                CellTileItem(
                    title = TextSource.Simple("Cell tile title"),
                    value = TextSource.Simple("Value"),
                ),

                CellTileItem(
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    link = TextSource.Simple("Link button"),
                    mainClickListener = {},
                ),

                CellTileItem(
                    leftPart = CellLeftPart.Icon(
                        ImageSource.Res(CoreUiR.drawable.ic_user),
                        ImageSource.Res(CoreUiR.drawable.bg_ic_square),
                    ),
                    rightPart = CellRightPart.Logo(ImageSource.Res(DemoAppR.drawable.demo_avatar)),
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    additional = TextSource.Simple("Additional text with some interesting information"),
                ),

                CellTileItem(
                    leftPart = CellLeftPart.Logo(ImageSource.Res(DemoAppR.drawable.demo_avatar)),
                    rightPart = CellRightPart.ActionIcon(ImageSource.Res(CoreUiR.drawable.ic_next)),
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    additional = TextSource.Simple("Additional text with some interesting information"),
                    mainClickListener = {},
                ),

                CellTileItem(
                    leftPart = CellLeftPart.Icon(ImageSource.Res(DemoAppR.drawable.demo_avatar)),
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    additional = TextSource.Simple("Additional text with some interesting information"),
                    rightPart = CellRightPart.Switch(true),
                    mainClickListener = {},
                ),

                CellTileItem.shimmer(),
            )
        )
    }
}