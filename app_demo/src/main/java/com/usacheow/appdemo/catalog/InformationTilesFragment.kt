package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.atom.DividerTileItem
import com.usacheow.coreui.uikit.container.ViewTypeHorizontalListItem
import com.usacheow.coreui.uikit.molecule.*
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class InformationTilesFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = InformationTilesFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.widgetsListView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Information tiles"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            HeaderTileItem(TextString("Header")),
            HeaderTileItem.shimmer(),
            SubtitleTileItem(TextString("Subtitle"), TextString("With action"), {}),
            SubtitleTileItem.shimmer(),

            DividerTileItem.getSmallDivider(),

            InformationTileItem(
                imageSource = ImageRes(R.drawable.demo_avatar),
                additionalLeftText = TextString("City 17"),
                additionalRightText = TextString("00/00/0000"),
                mainLeftText = TextString("Gordon"),
                mainRightText = TextString("(000) 000-00-00"),
            ),

            DividerTileItem.getSmallDivider(),

            ViewTypeHorizontalListItem(listOf(
                BadgeTileItem.shimmer(),
                BadgeTileItem(needAdaptWidth = true, header = TextString("Badge"), value = TextString("Clickable"), clickListener = {}),
                BadgeTileItem(needAdaptWidth = true, header = TextString("Badge"), value = TextString("Non-clickable"), backgroundColorRes = R.color.colorPrimary, textColorRes = R.color.white),
                BadgeTileItem(needAdaptWidth = true, header = TextString("Badge"), value = TextString("Clickable"), backgroundColorRes = R.color.error, textColorRes = R.color.white, clickListener = {}),
            )),

            ViewTypeHorizontalListItem(listOf(
                BannerTileItem.shimmer(),
                BannerTileItem(text = TextString("Some text on two lines"), icon = ImageRes(R.drawable.demo_avatar), clickListener = {}),
            )),
        ))
    }
}