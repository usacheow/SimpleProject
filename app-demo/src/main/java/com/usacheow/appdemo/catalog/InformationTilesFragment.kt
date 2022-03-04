package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.atom.DividerTileItem
import com.usacheow.coreuiview.container.ViewStateHorizontalListItem
import com.usacheow.coreuiview.helper.PaddingValue
import com.usacheow.coreuiview.helper.ThemeColorsAttrs
import com.usacheow.coreuiview.helper.applyBottomInset
import com.usacheow.coreuiview.helper.applyTopInset
import com.usacheow.coreuiview.helper.getBottomInset
import com.usacheow.coreuiview.helper.getTopInset
import com.usacheow.coreuiview.molecule.BadgeTileItem
import com.usacheow.coreuiview.molecule.BannerTileItem
import com.usacheow.coreuiview.molecule.HeaderTileItem
import com.usacheow.coreuiview.molecule.InformationTileItem
import com.usacheow.coreuiview.molecule.SubtitleTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class InformationTilesFragment : SimpleFragment<FragmentListBinding>() {

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
        binding.header.title = "Information tiles"
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_back, action = router::back)

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewStateAdapter(
            listOf(
                HeaderTileItem(TextSource.Simple("Header")),
                HeaderTileItem.shimmer(),
                SubtitleTileItem(TextSource.Simple("Subtitle"), TextSource.Simple("With action")) {},
                SubtitleTileItem.shimmer(),

                DividerTileItem.getSmallDivider(),

                InformationTileItem(
                    imageSource = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    additionalLeftText = TextSource.Simple("City 17"),
                    additionalRightText = TextSource.Simple("00/00/0000"),
                    mainLeftText = TextSource.Simple("Gordon"),
                    mainRightText = TextSource.Simple("(000) 000-00-00"),
                ),

                DividerTileItem.getSmallDivider(),

                ViewStateHorizontalListItem(
                    listOf(
                        BadgeTileItem.shimmer(),
                        BadgeTileItem(
                            needAdaptWidth = true,
                            header = TextSource.Simple("Badge"),
                            value = TextSource.Simple("Clickable"),
                            clickListener = {},
                        ),
                        BadgeTileItem(
                            needAdaptWidth = true,
                            header = TextSource.Simple("Badge"),
                            value = TextSource.Simple("Non-clickable"),
                            backgroundColor = ColorSource.fromAttr(ThemeColorsAttrs.primary),
                            textColor = ColorSource.fromAttr(ThemeColorsAttrs.onPrimary),
                        ),
                        BadgeTileItem(
                            needAdaptWidth = true,
                            header = TextSource.Simple("Badge"),
                            value = TextSource.Simple("Clickable"),
                            backgroundColor = ColorSource.fromAttr(ThemeColorsAttrs.error),
                            textColor = ColorSource.fromAttr(ThemeColorsAttrs.onError),
                            clickListener = {},
                        ),
                    )
                ),

                ViewStateHorizontalListItem(
                    listOf(
                        BannerTileItem.shimmer(),
                        BannerTileItem(
                            text = TextSource.Simple("Some text on two lines"),
                            icon = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                            clickListener = {},
                        ),
                    )
                ),
            )
        )
    }
}