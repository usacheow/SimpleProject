package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentTagListBinding
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.adapter.MultipleSelectionViewStatesAdapter
import com.usacheow.coreuiview.adapter.SingleSelectionViewStatesAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreuiview.uikit.molecule.TagTileItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class TagListFragment : SimpleFragment<FragmentTagListBinding>() {

    @Inject lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentTagListBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.widgetsListView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_back, action = router::back)

        binding.radioListView.isNestedScrollingEnabled = false
        binding.radioListView.layoutManager = GridLayoutManager(context, 2)
        binding.radioListView.adapter = SingleSelectionViewStatesAdapter(
            listOf(
                TagTileItem(TextSource.Simple("Radio tag 1")) {},
                TagTileItem(TextSource.Simple("Radio tag 2")) {},
                TagTileItem(TextSource.Simple("Radio tag 3")) {},
                TagTileItem(TextSource.Simple("Radio tag 4")) {},
                TagTileItem(TextSource.Simple("Radio tag 5")) {},
                TagTileItem(TextSource.Simple("Radio tag 6")) {},
                TagTileItem(TextSource.Simple("Radio tag 7")) {},
            )
        )

        binding.chipListView.isNestedScrollingEnabled = false
        binding.chipListView.layoutManager = GridLayoutManager(context, 2)
        binding.chipListView.adapter = MultipleSelectionViewStatesAdapter(
            listOf(
                TagTileItem(TextSource.Simple("Chip tag 1")) {},
                TagTileItem(TextSource.Simple("Chip tag 2")) {},
                TagTileItem(TextSource.Simple("Chip tag 3")) {},
                TagTileItem(TextSource.Simple("Chip tag 4")) {},
                TagTileItem(TextSource.Simple("Chip tag 5")) {},
                TagTileItem(TextSource.Simple("Chip tag 6")) {},
                TagTileItem(TextSource.Simple("Chip tag 7")) {},
            )
        )
    }
}