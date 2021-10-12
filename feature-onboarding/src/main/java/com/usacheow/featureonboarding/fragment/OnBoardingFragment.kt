package com.usacheow.featureonboarding.fragment

import android.os.Bundle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.getBottomInset
import com.usacheow.coreui.uikit.helper.getTopInset
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

    private val adapter = ViewTypesAdapter()

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.root.updatePadding(top = insets.getTopInset(), bottom = insets.getBottomInset())
        return insets
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.viewPager.adapter = adapter
        binding.indicatorView.attachToPager(binding.viewPager)

        binding.skipButton.setOnClickListener { viewModel.onOnBoardingSkipped() }
        binding.nextButton.setOnClickListener {
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