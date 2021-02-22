package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.atom.DividerTileItem
import com.usacheow.coreui.uikit.container.ViewTypeHorizontalListItem
import com.usacheow.coreui.uikit.molecule.*
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue

class InformationTilesFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = InformationTilesFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
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
            SubtitleTileItem(TextString("Subtitle"), TextString("With action")),

            DividerTileItem.getSmallDivider(),

            InformationTileItem(
                imageSource = ImageRes(R.drawable.demo_avatar),
                topLeftText = TextString("City 17"),
                rightTopText = TextString("00/00/0000"),
                leftMain = TextString("Gordon"),
                rightMain = TextString("(000) 000-00-00"),
            ),

            DividerTileItem.getSmallDivider(),

            ViewTypeHorizontalListItem(listOf(
                BadgeTileItem.shimmer(),
                BadgeTileItem(needAdapt = true, header = TextString("Badge"), value = TextString("Clickable"), clickAction = {}),
                BadgeTileItem(needAdapt = true, header = TextString("Badge"), value = TextString("Non-clickable"), backgroundColorRes = R.color.colorAccent, textColorRes = R.color.white),
                BadgeTileItem(needAdapt = true, header = TextString("Badge"), value = TextString("Clickable"), backgroundColorRes = R.color.error, textColorRes = R.color.white, clickAction = {}),
            )),

            ViewTypeHorizontalListItem(listOf(
                BannerTileItem(text = TextString("Some text on two lines"), icon = ImageRes(R.drawable.ic_money), clickAction = {}),
                BannerTileItem.shimmer(),
            )),
        ))
    }
}