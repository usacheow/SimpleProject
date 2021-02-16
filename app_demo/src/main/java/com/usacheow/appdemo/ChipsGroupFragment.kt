package com.usacheow.appdemo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.databinding.FragmentChipsGroupBinding
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.uikit.molecule.Filter
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doWithTransitionOnParentView

class ChipsGroupFragment : SimpleFragment<FragmentChipsGroupBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentChipsGroupBinding::inflate,
    )

    companion object {
        fun newInstance() = ChipsGroupFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue) {
        val isKeyboardVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom != 0
        doWithTransitionOnParentView {
            binding.viewsScrollView.updatePadding(bottom = when (isKeyboardVisible) {
                true -> insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                false -> insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            })
        }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Chips group"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.chipsLayout.populate(setOf(
            Filter(1, "Chip 1", true),
            Filter(2, "Chip 2", false),
            Filter(3, "Chip 3", false),
            Filter(4, "Chip 4", false),
            Filter(5, "Chip 5", false)
        )) { _, _ -> }
    }
}