package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.ListTileItem
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class ListTilesFragment : SimpleFragment<FragmentListBinding>() {

    override val defaultParams = Params(
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
        binding.widgetsListView.adapter = ViewTypesAdapter(
            listOf(
                ListTileItem(
                    value = TextSource.Simple("Title"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(R.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(R.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                    topDescription = TextSource.Simple("Top description"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(R.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                    bottomDescription = TextSource.Simple("Bottom description"),
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(R.drawable.demo_avatar),
                    value = TextSource.Simple("Title"),
                    topDescription = TextSource.Simple("Top description"),
                    bottomDescription = TextSource.Simple("Bottom description"),
                    clickListener = {},
                ),

                ListTileItem(
                    leftImageInfo = ImageSource.Res(R.drawable.demo_avatar),
                    rightImageInfo = ImageSource.Res(R.drawable.ic_go),
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