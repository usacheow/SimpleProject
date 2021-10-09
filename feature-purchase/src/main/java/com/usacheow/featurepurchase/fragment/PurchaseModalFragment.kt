package com.usacheow.featurepurchase.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.featurepurchase.R
import com.example.featurepurchase.databinding.FragmentPurchaseBinding
import com.usacheow.core.ImageSource
import com.usacheow.core.TextSource
import com.usacheow.corebilling.BillingRouter
import com.usacheow.coreui.adapter.SingleSelectionViewTypesAdapter
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleModalFragment
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.doOnClick
import com.usacheow.featurepurchase.view.AdvantageTileItem
import com.usacheow.featurepurchase.viewmodel.PurchaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PurchaseModalFragment : SimpleModalFragment<FragmentPurchaseBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentPurchaseBinding::inflate,
    )

    @Inject lateinit var router: BillingRouter

    private val viewModel by viewModels<PurchaseViewModel>()
    private val advantagesAdapter = ViewTypesAdapter(
        listOf(
            AdvantageTileItem(
                image = ImageSource.Res(R.drawable.ic_money),
                title = TextSource.Res(R.string.purchase_advantage_1_title),
                info = TextSource.Res(R.string.purchase_advantage_1_info),
            ),
            AdvantageTileItem(
                image = ImageSource.Res(R.drawable.ic_money),
                title = TextSource.Res(R.string.purchase_advantage_2_title),
                info = TextSource.Res(R.string.purchase_advantage_2_info),
            ),
            AdvantageTileItem(
                image = ImageSource.Res(R.drawable.ic_money),
                title = TextSource.Res(R.string.purchase_advantage_3_title),
                info = TextSource.Res(R.string.purchase_advantage_3_info),
            ),
        )
    )

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.closeButton.doOnClick { dismiss() }

        binding.advantagesListView.adapter = advantagesAdapter
        binding.indicatorView.attachToPager(binding.advantagesListView)
        binding.priceListView.isNestedScrollingEnabled = false

        binding.buyButton.doOnClick { viewModel.onBuyClicked() }
    }

    override fun subscribe() {
        viewModel.productsState.observe(viewLifecycleOwner) {
            binding.priceListView.adapter = SingleSelectionViewTypesAdapter(it.prices)
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