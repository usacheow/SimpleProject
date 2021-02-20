package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.*
import com.usacheow.coreui.utils.IconInfo
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.LogoInfo
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue

class ListTileFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = ListTileFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "List tile item"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            ListTileItem(
                title = TextString("Title"),
            ),

            ListTileItem(
                leftImageInfo = IconInfo(source = ImageRes(R.drawable.demo_avatar)),
                title = TextString("Title"),
            ),

            ListTileItem(
                leftImageInfo = IconInfo(source = ImageRes(R.drawable.demo_avatar)),
                title = TextString("Title"),
                topDescription = TextString("Top description"),
            ),

            ListTileItem(
                leftImageInfo = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                title = TextString("Title"),
                bottomDescription = TextString("Bottom description"),
            ),

            ListTileItem(
                leftImageInfo = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                title = TextString("Title"),
                topDescription = TextString("Top description"),
                bottomDescription = TextString("Bottom description"),
                onItemClicked = {},
            ),

            ListTileItem(
                leftImageInfo = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                rightImageInfo = IconInfo(source = ImageRes(R.drawable.ic_next)),
                title = TextString("Title"),
                topDescription = TextString("Top description"),
                bottomDescription = TextString("Bottom description"),
                onItemClicked = {},
            ),

            ListTileItem.shimmer(),
        ))
    }
}