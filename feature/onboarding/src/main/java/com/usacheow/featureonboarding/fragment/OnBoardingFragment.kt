package com.usacheow.featureonboarding.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import com.usacheow.coreuiview.adapter.ViewStateAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.helper.PaddingValue
import com.usacheow.coreuiview.helper.applyInsets
import com.usacheow.coreuiview.helper.doOnClick
import com.usacheow.coreuiview.helper.getBottomInset
import com.usacheow.coreuiview.helper.getTopInset
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.featureonboarding.databinding.FragmentOnboardingBinding
import com.usacheow.featureonboarding.navigation.OnBoardingRouter
import com.usacheow.featureonboarding.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : SimpleFragment<FragmentOnboardingBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentOnboardingBinding::inflate,
    )

    @Inject lateinit var router: OnBoardingRouter

    private val viewModel by viewModels<OnBoardingViewModel>()

    private val adapter = ViewStateAdapter()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.root.applyInsets(top = insets.getTopInset(), bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.viewPager.adapter = adapter
        binding.indicatorView.attachToPager(binding.viewPager)

        binding.skipButton.doOnClick { viewModel.onOnBoardingSkipped() }
        binding.nextButton.doOnClick {
            if (binding.viewPager.currentItem < adapter.itemCount - 1) {
                binding.viewPager.currentItem += 1
            } else {
                viewModel.onOnBoardingFinished()
            }
        }
    }

    override fun subscribe() {
        viewModel.pagesState.observe(viewLifecycleOwner, adapter::update)
        viewModel.openAppScreenAction.observe(viewLifecycleOwner, router::apply)
    }
}