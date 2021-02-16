package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.RadioViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.TagItem
import com.usacheow.coreui.utils.view.PaddingValue

class RadioTagListFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = RadioTagListFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Radio tag list"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.widgetsListView.adapter = RadioViewTypesAdapter(listOf(
            TagItem("Tag 1") {},
            TagItem("Tag 2") {},
            TagItem("Tag 3") {},
            TagItem("Tag 4") {},
            TagItem("Tag 5") {},
            TagItem("Tag 6") {},
            TagItem("Tag 7") {},
        ))
    }
}