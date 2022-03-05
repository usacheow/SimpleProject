package com.usacheow.featurepurchase.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.example.featurepurchase.databinding.FragmentPurchaseBinding
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.TextSource
import com.usacheow.corebilling.BillingRouter
import com.usacheow.coreuiview.adapter.SingleSelectionViewStatesAdapter
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleModalFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.applyTopInset
import com.usacheow.coreuiview.tools.doOnClick
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.getTopInset
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.featurepurchase.view.AdvantageTileItem
import com.usacheow.featurepurchase.viewmodel.PurchaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.corecommon.R as CoreR
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class PurchaseModalFragment : SimpleModalFragment<FragmentPurchaseBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentPurchaseBinding::inflate,
    )

    @Inject lateinit var router: BillingRouter

    private val viewModel by viewModels<PurchaseViewModel>()
    private val advantagesAdapter = ViewStateAdapter(
        listOf(
            AdvantageTileItem(
                image = ImageSource.Res(CoreUiThemeR.drawable.ic_money),
                title = TextSource.Res(CoreR.string.purchase_advantage_1_title),
                info = TextSource.Res(CoreR.string.purchase_advantage_1_info),
            ),
            AdvantageTileItem(
                image = ImageSource.Res(CoreUiThemeR.drawable.ic_money),
                title = TextSource.Res(CoreR.string.purchase_advantage_2_title),
                info = TextSource.Res(CoreR.string.purchase_advantage_2_info),
            ),
            AdvantageTileItem(
                image = ImageSource.Res(CoreUiThemeR.drawable.ic_money),
                title = TextSource.Res(CoreR.string.purchase_advantage_3_title),
                info = TextSource.Res(CoreR.string.purchase_advantage_3_info),
            ),
        )
    )

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.header.applyTopInset(insets.getTopInset())
        binding.scrollView.applyBottomInset(insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.header.setNavigationAction(CoreUiThemeR.drawable.ic_close, action = ::dismiss)

        binding.advantagesListView.adapter = advantagesAdapter
        binding.indicatorView.attachToPager(binding.advantagesListView)
        binding.priceListView.isNestedScrollingEnabled = false

        binding.buyButton.doOnClick { viewModel.onBuyClicked() }
    }

    override fun subscribe() {
        viewModel.productsState.observe(viewLifecycleOwner) {
            binding.priceListView.adapter = SingleSelectionViewStatesAdapter(it.prices)
        }
        viewModel.buyButtonTextState.observe(viewLifecycleOwner) {
            binding.buyButton.text = it
        }
        viewModel.openPurchaseScreenAction.observe(viewLifecycleOwner) {
            router.openBillingScreen(it, requireActivity())
        }
        viewModel.closeScreenAction.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }
    }
}