package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.MainMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.navigation.FeatureNavDirection
import com.usacheow.coreui.utils.navigation.OPEN_IN
import com.usacheow.coreui.utils.navigation.REPLACING
import com.usacheow.coreui.utils.navigation.ResetTo
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.coreui.utils.navigation.toFeatureNavDirection
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenActivity : SimpleActivity<ActivityHostBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )

    @Inject lateinit var authorizationMediator: AuthorizationMediator
    @Inject lateinit var onBoardingMediator: OnBoardingMediator
    @Inject lateinit var mainMediator: MainMediator

    private val viewModel by viewModels<MainScreenViewModel>()

    private var isKeyboardVisible = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isKeyboardVisible) {
                windowInsetsController?.hideIme()
            } else {
                isEnabled = false
                onBackPressed()
                isEnabled = true
            }
        }
    }

    override fun findNavController() = findNavController(R.id.fragmentContainer)

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        isKeyboardVisible = insets.isImeVisible()
        return insets
    }

    override fun initSplashScreen() {
        installSplashScreen()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun subscribe() {
        viewModel.openAuthScreenAction.observe(this) {
            val nextDirection = FeatureNavDirection(R.id.bottomBarFragment, resetTo = ResetTo(R.id.app_nav_graph))
            navigateTo(authorizationMediator.getSignInWithPhoneFlowDirection(nextDirection))
        }
        viewModel.openPinScreenAction.observe(this) {
            val nextDirection = FeatureNavDirection(R.id.bottomBarFragment, resetTo = ResetTo(R.id.app_nav_graph))
            navigateTo(authorizationMediator.getPinCodeFlowDirection(nextDirection))
        }
        viewModel.openOnBoardingScreenAction.observe(this) {
            val nextDirection = FeatureNavDirection(R.id.bottomBarFragment, resetTo = ResetTo(R.id.app_nav_graph))
            navigateTo(onBoardingMediator.getOnBoardingFlowDirection(nextDirection))
        }
        viewModel.openAppScreenAction.observe(this) {
            navigateTo(screen(R.id.bottomBarFragment))
        }
    }

    private fun navigateTo(direction: NavDirections) {
        direction.toFeatureNavDirection() REPLACING R.id.app_nav_graph OPEN_IN findNavController(R.id.fragmentContainer)
    }
}