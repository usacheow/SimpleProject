package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.atom.DividerItem
import com.usacheow.coreui.uikit.container.ViewTypeHorizontalListItem
import com.usacheow.coreui.uikit.molecule.HeaderWithActionItem
import com.usacheow.coreui.uikit.molecule.InformationItem
import com.usacheow.coreui.uikit.molecule.BadgeItem
import com.usacheow.coreui.uikit.molecule.BannerItem
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue

class InformationItemsFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = InformationItemsFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Information items"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            HeaderWithActionItem(TextString("Header item"), TextString("With action")),

            DividerItem.getSmallDivider(),

            InformationItem(
                imageSource = ImageRes(R.drawable.demo_avatar),
                topLeftText = TextString("City 17"),
                rightTopText = TextString("00/00/0000"),
                leftMain = TextString("Gordon"),
                rightMain = TextString("(000) 000-00-00"),
            ),

            DividerItem.getSmallDivider(),

            ViewTypeHorizontalListItem(listOf(
                BadgeItem.shimmer(),
                BadgeItem(header = TextString("Badge"), value = TextString("Clickable"), clickAction = {}),
                BadgeItem(header = TextString("Badge"), value = TextString("Non-clickable"), backgroundColorRes = R.color.colorAccent, textColorRes = R.color.white),
                BadgeItem(header = TextString("Badge"), value = TextString("Clickable"), backgroundColorRes = R.color.error, textColorRes = R.color.white, clickAction = {}),
            )),

            ViewTypeHorizontalListItem(listOf(
                BannerItem(text = TextString("Some text on two lines"), icon = ImageRes(R.drawable.ic_money), clickAction = {}),
                BannerItem.shimmer(),
            )),
        ))
    }
}