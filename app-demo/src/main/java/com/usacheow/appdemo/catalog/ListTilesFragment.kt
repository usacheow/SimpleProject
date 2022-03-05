package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreuiview.uikit.molecule.ListTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class ListTilesFragment : SimpleFragment<FragmentListBinding>() {

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
        binding.header.title = "List tiles"
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_back, action = router::back)

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewStateAdapter(
            listOf(
                ListTileItem(
                    value = TextSource.Simple("Title"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                    topDescription = TextSource.Simple("Top description"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                    bottomDescription = TextSource.Simple("Bottom description"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                    topDescription = TextSource.Simple("Top description"),
                    bottomDescription = TextSource.Simple("Bottom description"),
                    clickListener = {},
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                    rightImageInfo = ImageSource.Res(DemoAppR.drawable.ic_go),
                    value = TextSource.Simple("Title"),
                    topDescription = TextSource.Simple("Top description"),
                    bottomDescription = TextSource.Simple("Bottom description"),
                    clickListener = {},
                ),

                ListTileItem.shimmer(),
            )
        )
    }
}