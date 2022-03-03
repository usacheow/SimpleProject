package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.corecommon.resource.ImageSource
import com.usacheow.corecommon.resource.TextSource
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.helper.PaddingValue
import com.usacheow.coreuiview.helper.applyBottomInset
import com.usacheow.coreuiview.helper.applyTopInset
import com.usacheow.coreuiview.helper.getBottomInset
import com.usacheow.coreuiview.helper.getTopInset
import com.usacheow.coreuiview.molecule.CellLeftPart
import com.usacheow.coreuiview.molecule.CellRightPart
import com.usacheow.coreuiview.molecule.CellTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.coreuitheme.R as CoreUiThemeR

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
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_back, action = router::back)

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
                        ImageSource.Res(CoreUiThemeR.drawable.ic_user),
                        ImageSource.Res(CoreUiThemeR.drawable.bg_ic_square),
                    ),
                    rightPart = CellRightPart.Logo(ImageSource.Res(DemoAppR.drawable.demo_avatar)),
                    title = TextSource.Simple("Cell tile title"),
                    subtitle = TextSource.Simple("Subtitle"),
                    additional = TextSource.Simple("Additional text with some interesting information"),
                ),

                CellTileItem(
                    leftPart = CellLeftPart.Logo(ImageSource.Res(DemoAppR.drawable.demo_avatar)),
                    rightPart = CellRightPart.ActionIcon(ImageSource.Res(CoreUiThemeR.drawable.ic_next)),
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