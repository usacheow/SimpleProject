package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.R as CoreUiR

class ListTilesFragment : SimpleFragment<FragmentListBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.widgetsListView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "List tiles"
            setNavigationAction(CoreUiR.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

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