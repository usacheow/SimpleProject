package com.usacheow.appdemo.catalog

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import com.usacheow.appdemo.DemoRouter
import com.usacheow.appdemo.databinding.FragmentErrorMessageBinding
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreuiview.uikit.organism.MessageBannerItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.appdemo.R as DemoAppR
import com.usacheow.corecommon.R as CoreR
import com.usacheow.coreuitheme.R as CoreUiThemeR

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
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_back, action = router::back)

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