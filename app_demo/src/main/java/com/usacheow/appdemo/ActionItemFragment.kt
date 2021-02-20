package com.usacheow.appdemo

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.appdemo.databinding.FragmentListBinding
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.*
import com.usacheow.coreui.utils.*
import com.usacheow.coreui.utils.view.PaddingValue

class ActionItemFragment : SimpleFragment<FragmentListBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentListBinding::inflate,
    )

    companion object {
        fun newInstance() = ActionItemFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        binding.widgetsListView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom,
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Action item"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.widgetsListView.layoutManager = LinearLayoutManager(context)
        binding.widgetsListView.adapter = ViewTypesAdapter(listOf(
            ActionItem(
                title = TextInfo(TextString("Title")),
            ),

            ActionItem(
                title = TextInfo(TextString("Title")),
                subtitle = TextInfo(TextString("Subtitle")),
                selectionType = ActionSelectionType.SWITCH,
            ),

            ActionItem(
                image = IconInfo(source = ImageRes(R.drawable.demo_avatar)),
                title = TextInfo(TextString("Title")),
                subtitle = TextInfo(TextString("Subtitle")),
                selectionType = ActionSelectionType.CHECK_BOX,
                onControlClicked = {},
            ),

            ActionItem(
                image = LogoInfo(source = ImageRes(R.drawable.demo_avatar)),
                title = TextInfo(TextString("Title")),
                subtitle = TextInfo(TextString("Subtitle")),
                selectionType = ActionSelectionType.SWITCH,
                isChecked = true,
                onControlClicked = {},
            ),

            ActionItem.shimmer(),
        ))
    }
}