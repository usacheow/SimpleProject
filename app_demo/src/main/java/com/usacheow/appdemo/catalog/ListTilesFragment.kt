package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.*
import com.usacheow.coreui.utils.IconInfo
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.LogoInfo
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class ListTilesFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = ListTilesFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.widgetsListView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "List tiles"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            ListTileItem(
                value = TextString("Title"),
            ),

            ListTileItem(
                leftImageInfo = IconInfo(source = ImageRes(R.drawable.demo_avatar)),
                value = TextString("Title"),
            ),

            ListTileItem(
                leftImageInfo = IconInfo(source = ImageRes(R.drawable.demo_avatar)),
                value = TextString("Title"),
                topDescription = TextString("Top description"),
            ),

            ListTileItem(
                leftImageInfo = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                value = TextString("Title"),
                bottomDescription = TextString("Bottom description"),
            ),

            ListTileItem(
                leftImageInfo = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                value = TextString("Title"),
                topDescription = TextString("Top description"),
                bottomDescription = TextString("Bottom description"),
                clickListener = {},
            ),

            ListTileItem(
                leftImageInfo = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                rightImageInfo = IconInfo(source = ImageRes(R.drawable.ic_next)),
                value = TextString("Title"),
                topDescription = TextString("Top description"),
                bottomDescription = TextString("Bottom description"),
                clickListener = {},
            ),

            ListTileItem.shimmer(),
        ))
    }
}