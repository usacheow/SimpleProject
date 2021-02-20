package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentTagListBinding
import com.usacheow.coreui.adapters.MultipleSelectionViewTypesAdapter
import com.usacheow.coreui.adapters.SingleSelectionViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.TagItem
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue

class TagListFragment : SimpleFragment<FragmentTagListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentTagListBinding::inflate,
    )

    companion object {
        fun newInstance() = TagListFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Tag list"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.radioListView.isNestedScrollingEnabled = false
        binding.radioListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.radioListView.adapter = SingleSelectionViewTypesAdapter(listOf(
            TagItem(TextString("Radio tag 1")) {},
            TagItem(TextString("Radio tag 2")) {},
            TagItem(TextString("Radio tag 3")) {},
            TagItem(TextString("Radio tag 4")) {},
            TagItem(TextString("Radio tag 5")) {},
            TagItem(TextString("Radio tag 6")) {},
            TagItem(TextString("Radio tag 7")) {},
        ))

        binding.chipListView.isNestedScrollingEnabled = false
        binding.chipListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.chipListView.adapter = MultipleSelectionViewTypesAdapter(listOf(
            TagItem(TextString("Chip tag 1")) {},
            TagItem(TextString("Chip tag 2")) {},
            TagItem(TextString("Chip tag 3")) {},
            TagItem(TextString("Chip tag 4")) {},
            TagItem(TextString("Chip tag 5")) {},
            TagItem(TextString("Chip tag 6")) {},
            TagItem(TextString("Chip tag 7")) {},
        ))
    }
}