package com.usacheow.featurehello.presentation.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.header.SimpleAppBarLayout
import com.usacheow.coreui.uikit.listitem.ActionItem
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.toPx
import com.usacheow.featurehello.R
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_a.header
import kotlinx.android.synthetic.main.fragment_a.listView

@AndroidEntryPoint
class AFragment : SimpleFragment() {

    private val viewModel by viewModels<AViewModel>({ requireParentFragment() })

    override val layoutId = R.layout.fragment_a

    companion object {
        fun newInstance() = AFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        listView.updatePadding(
            bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        )
    }

    override fun getSharedViews() = listOf(header)

    override fun setupViews(savedInstanceState: Bundle?) {
        (header as SimpleAppBarLayout).apply {
            title = "A Fragment ${viewModel.x}"
            setBackground(R.color.colorGreyCard)
        }

        listView.layoutManager = LinearLayoutManager(context)
        listView.adapter = ViewTypesAdapter(listOf(
            ActionItem(title = "1 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "2 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "3 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "4 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "5 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "6 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "7 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "8 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "9 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "10 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "11 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "12 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "13 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "14 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "15 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "16 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "17 Go to next screen", onItemClicked = ::openNextScreen),
            ActionItem(title = "18 Go to next screen", onItemClicked = ::openNextScreen)
        ))
    }

    private fun openNextScreen() {
        viewModel.x++
        getContainer { show(BFragment()) }
    }
}