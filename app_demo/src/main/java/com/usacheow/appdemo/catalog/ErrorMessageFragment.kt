package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.usacheow.appdemo.R
import com.usacheow.appdemo.databinding.FragmentErrorMessageBinding
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.uikit.organism.MessageBannerItem
import com.usacheow.coreui.utils.ImageRes
import com.usacheow.coreui.utils.TextRes
import com.usacheow.coreui.utils.TextString
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.getTopInset

class ErrorMessageFragment : SimpleFragment<FragmentErrorMessageBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentErrorMessageBinding::inflate,
    )

    companion object {
        fun newInstance() = ErrorMessageFragment()
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.root.applyInsets(insets.getTopInset())
        binding.viewsScrollView.updatePadding(bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.root.apply {
            title = "Error message view"
            setNavigationAction(R.drawable.ic_back) {
                requireActivity().onBackPressed()
            }
        }

        binding.errorMessageView1.showOrHideError(
            MessageBannerItem(
                title = TextString("Error title"),
                description = TextString("Error description"),
            )
        )
        binding.errorMessageView2.showOrHideError(
            MessageBannerItem(
                title = TextString("Error title"),
                description = TextString("Error description"),
                button = TextRes(R.string.repeat),
                clickListener = {},
            )
        )
        binding.errorMessageView3.showOrHideError(
            MessageBannerItem(
                icon = ImageRes(R.drawable.demo_avatar),
                title = TextString("Error title"),
                description = TextString("Error description"),
            )
        )
        binding.errorMessageView4.showOrHideError(
            MessageBannerItem(
                icon = ImageRes(R.drawable.demo_avatar),
                title = TextString("Error title"),
                description = TextString("Error description"),
                button = TextRes(R.string.repeat),
                clickListener = {},
            )
        )
    }
}