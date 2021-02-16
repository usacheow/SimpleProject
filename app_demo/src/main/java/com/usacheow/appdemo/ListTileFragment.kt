package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.atom.DividerItem
import com.usacheow.coreui.uikit.atom.SimpleButtonItem
import com.usacheow.coreui.uikit.atom.SimpleOutlinedButtonItem
import com.usacheow.coreui.uikit.atom.SimpleTextButtonItem
import com.usacheow.coreui.uikit.molecule.*
import com.usacheow.coreui.utils.IconState
import com.usacheow.coreui.utils.ImageState
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
                title = "Title",
            ),

            ListTileItem(
                imageInfo = IconState(resId = R.drawable.ic_phone),
                title = "Title",
            ),

            ListTileItem(
                imageInfo = IconState(resId = R.drawable.ic_phone),
                title = "Title",
                subtitle = "Subtitle",
            ),

            ListTileItem(
                imageInfo = IconState(resId = R.drawable.ic_phone),
                title = "Title",
                subtitle = "Subtitle",
                description = "Description",
                onItemClicked = {},
            ),

            ListTileItem.shimmer(),
        ))
    }
}