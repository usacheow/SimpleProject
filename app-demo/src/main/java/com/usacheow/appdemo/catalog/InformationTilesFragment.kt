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
import com.usacheow.coreui.uikit.atom.DividerTileItem
import com.usacheow.coreui.uikit.container.ViewTypeHorizontalListItem
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.molecule.BadgeTileItem
import com.usacheow.coreui.uikit.molecule.BannerTileItem
import com.usacheow.coreui.uikit.molecule.HeaderTileItem
import com.usacheow.coreui.uikit.molecule.InformationTileItem
import com.usacheow.coreui.uikit.molecule.SubtitleTileItem
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.coreui.R as CoreUiR

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
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewStateAdapter(
            listOf(
                HeaderTileItem(TextSource.Simple("Header")),
                HeaderTileItem.shimmer(),
                SubtitleTileItem(TextSource.Simple("Subtitle"), TextSource.Simple("With action"), {}),
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

                ViewTypeHorizontalListItem(
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
                            backgroundColorRes = CoreUiR.color.primary,
                            textColorRes = CoreUiR.color.white,
                        ),
                        BadgeTileItem(
                            needAdaptWidth = true,
                            header = TextSource.Simple("Badge"),
                            value = TextSource.Simple("Clickable"),
                            backgroundColorRes = CoreUiR.color.error,
                            textColorRes = CoreUiR.color.white,
                            clickListener = {},
                        ),
                    )
                ),

                ViewTypeHorizontalListItem(
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