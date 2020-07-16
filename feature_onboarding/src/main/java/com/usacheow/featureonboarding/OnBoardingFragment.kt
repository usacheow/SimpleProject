package com.usacheow.featureonboarding

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.adapters.ViewTypesAdapter
import com.usacheow.coreui.fragments.SimpleFragment
import com.usacheow.coreui.viewmodels.ViewModelFactory
import com.usacheow.diprovider.DiApp
import kotlinx.android.synthetic.main.fragment_onboarding.onBoardingNextButton
import kotlinx.android.synthetic.main.fragment_onboarding.onBoardingSkipButton
import kotlinx.android.synthetic.main.fragment_onboarding.onBoardingViewPager
import javax.inject.Inject

class OnBoardingFragment : SimpleFragment() {

    override val layoutId = R.layout.fragment_onboarding

    @Inject lateinit var factory: ViewModelFactory
    private val viewModel by viewModels<AppStateViewModel>({ requireActivity() }, { factory })

    private val onBoardingData = mutableListOf(
        OnBoardingItem(R.drawable.on_boarding_1, R.string.on_boarding_title_1, R.string.on_boarding_description_1),
        OnBoardingItem(R.drawable.on_boarding_2, R.string.on_boarding_title_2, R.string.on_boarding_description_2),
        OnBoardingItem(R.drawable.on_boarding_3, R.string.on_boarding_title_3, R.string.on_boarding_description_3)
    )
    private val adapter = ViewTypesAdapter(onBoardingData)

    companion object {
        fun getInstance() = OnBoardingFragment()
    }

    override fun inject(application: Application) {
        OnBoardingComponent.init((application as DiApp).diProvider).inject(this)
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
