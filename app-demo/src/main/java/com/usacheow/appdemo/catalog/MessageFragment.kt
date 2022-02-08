package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentErrorMessageBinding
import com.usacheow.core.resource.ImageSource
import com.usacheow.core.resource.TextSource
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.applyBottomInset
import com.usacheow.coreui.uikit.helper.applyTopInset
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
import com.usacheow.coreui.uikit.organism.MessageBannerItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.core.R as CoreR
import com.usacheow.coreui.R as CoreUiR

@AndroidEntryPoint
class MessageFragment : SimpleFragment<FragmentErrorMessageBinding>() {

    @Inject lateinit var router: DemoRouter

    override val defaultParams = Params(
        viewBindingProvider = FragmentErrorMessageBinding::inflate,
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.viewsScrollView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiR.drawable.ic_back, action = router::back)

        binding.errorMessageView1.showOrHideError(
            MessageBannerItem(
                title = TextSource.Simple("Error title"),
                description = TextSource.Simple("Error description"),
            )
        )
        binding.errorMessageView2.showOrHideError(
            MessageBannerItem(
                title = TextSource.Simple("Error title"),
                description = TextSource.Simple("Error description"),
                button = TextSource.Res(CoreR.string.repeat),
                clickListener = {},
            )
        )
        binding.errorMessageView3.showOrHideError(
            MessageBannerItem(
                icon = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                title = TextSource.Simple("Error title"),
                description = TextSource.Simple("Error description"),
            )
        )
        binding.errorMessageView4.showOrHideError(
            MessageBannerItem(
                icon = ImageSource.Res(DemoAppR.drawable.demo_avatar),
                title = TextSource.Simple("Error title"),
                description = TextSource.Simple("Error description"),
                button = TextSource.Res(CoreR.string.repeat),
                clickListener = {},
            )
        )
    }
}