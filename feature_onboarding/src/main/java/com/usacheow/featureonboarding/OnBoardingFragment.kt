package com.usacheow.featureonboarding

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.usacheow.appshared.AppStateViewModel
import com.usacheow.coreui.adapter.ViewTypesAdapter
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.featureonboarding.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : SimpleFragment<FragmentOnboardingBinding>() {

    override val params = Params(
        viewBindingProvider = FragmentOnboardingBinding::inflate,
    )

    private val viewModel by activityViewModels<AppStateViewModel>()

    private val onBoardingData = mutableListOf(
        OnBoardingItem(R.drawable.on_boarding_1, R.string.on_boarding_title_1, R.string.on_boarding_description_1),
        OnBoardingItem(R.drawable.on_boarding_2, R.string.on_boarding_title_2, R.string.on_boarding_description_2),
        OnBoardingItem(R.drawable.on_boarding_3, R.string.on_boarding_title_3, R.string.on_boarding_description_3)
    )
    private val adapter = ViewTypesAdapter(onBoardingData)

    companion object {
        fun newInstance() = OnBoardingFragment()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.viewPager.adapter = adapter
        binding.indicatorView.attachToPager(binding.viewPager)

        binding.skipButton.setOnClickListener { startNextScreen() }
        binding.nextButton.setOnClickListener {
            if (binding.viewPager.currentItem < onBoardingData.size - 1) {
                binding.viewPager.currentItem += 1
            } else {
                startNextScreen()
            }
        }
    }

    private fun startNextScreen() {
        viewModel.onOnBoardingFinished()
    }
}
