package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentTagListBinding
import com.usacheow.coreui.adapter.MultipleSelectionViewTypesAdapter
import com.usacheow.coreui.adapter.SingleSelectionViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.molecule.TagTileItem
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class TagListFragment : SimpleFragment<FragmentTagListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentTagListBinding::inflate,
    )

    companion object {
        fun newInstance() = TagListFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.widgetsListView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Tag list"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.radioListView.isNestedScrollingEnabled = false
        binding.radioListView.layoutManager = GridLayoutManager(context, 2)
        binding.radioListView.adapter = SingleSelectionViewTypesAdapter(listOf(
            TagTileItem(TextString("Radio tag 1")) {},
            TagTileItem(TextString("Radio tag 2")) {},
            TagTileItem(TextString("Radio tag 3")) {},
            TagTileItem(TextString("Radio tag 4")) {},
            TagTileItem(TextString("Radio tag 5")) {},
            TagTileItem(TextString("Radio tag 6")) {},
            TagTileItem(TextString("Radio tag 7")) {},
        ))

        binding.chipListView.isNestedScrollingEnabled = false
        binding.chipListView.layoutManager = GridLayoutManager(context, 2)
        binding.chipListView.adapter = MultipleSelectionViewTypesAdapter(listOf(
            TagTileItem(TextString("Chip tag 1")) {},
            TagTileItem(TextString("Chip tag 2")) {},
            TagTileItem(TextString("Chip tag 3")) {},
            TagTileItem(TextString("Chip tag 4")) {},
            TagTileItem(TextString("Chip tag 5")) {},
            TagTileItem(TextString("Chip tag 6")) {},
            TagTileItem(TextString("Chip tag 7")) {},
        ))
    }
}