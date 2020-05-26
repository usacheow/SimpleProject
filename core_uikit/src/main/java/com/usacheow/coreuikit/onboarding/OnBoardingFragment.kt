package com.usacheow.coreuikit.onboarding

import android.os.Bundle
import com.usacheow.coreuikit.AppStateViewModel
import com.usacheow.coreuikit.R
import com.usacheow.coreuikit.adapters.ViewTypePagerAdapter
import com.usacheow.coreuikit.fragments.SimpleFragment
import com.usacheow.coreuikit.viewmodels.ViewModelFactory
import com.usacheow.coreuikit.viewmodels.injectViewModel
import com.usacheow.diprovider.DiProvider
import kotlinx.android.synthetic.main.fragment_onboarding.onBoardingNextButton
import kotlinx.android.synthetic.main.fragment_onboarding.onBoardingSkipButton
import kotlinx.android.synthetic.main.fragment_onboarding.onBoardingViewPager
import javax.inject.Inject

class OnBoardingFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_onboarding

    @Inject lateinit var factory: ViewModelFactory
    private val viewModel by injectViewModel<AppStateViewModel>({ requireActivity() }, { factory })

    private val onBoardingData = mutableListOf(
        OnBoardingItem(R.drawable.on_boarding_1, R.string.on_boarding_title_1, R.string.on_boarding_description_1),
        OnBoardingItem(R.drawable.on_boarding_2, R.string.on_boarding_title_2, R.string.on_boarding_description_2),
        OnBoardingItem(R.drawable.on_boarding_3, R.string.on_boarding_title_3, R.string.on_boarding_description_3)
    )
    private val adapter = ViewTypePagerAdapter(onBoardingData)

    override fun inject(diProvider: DiProvider) {
        OnBoardingComponent.init(diProvider).inject(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        onBoardingViewPager.adapter = adapter

        onBoardingSkipButton.setOnClickListener { startNextScreen() }
        onBoardingNextButton.setOnClickListener {
            if (onBoardingViewPager.currentItem < onBoardingData.size - 1) {
                onBoardingViewPager.currentItem += 1
            } else {
                startNextScreen()
            }
        }
    }

    private fun startNextScreen() {
        viewModel.onOnBoardingFinished()
    }
}
