package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.usacheow.appstate.AppStateViewModel
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coremediator.MainMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.activity.SimpleActivity
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

    override val params = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )

    @Inject lateinit var authorizationMediator: AuthorizationMediator
    @Inject lateinit var onBoardingMediator: OnBoardingMediator
    @Inject lateinit var mainMediator: MainMediator

    private val appStateViewModel by viewModels<AppStateViewModel>()

    private var isKeyboardVisible = false
    private val resetNavigationOptions = NavOptions.Builder()
        .setPopUpTo(R.id.app_nav_graph, true)
        .setLaunchSingleTop(true)
        .build()

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

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        isKeyboardVisible = insets.isImeVisible()
        return insets
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun subscribe() {
        appStateViewModel.openAuthScreenAction.observe(lifecycle) {
            navigateTo(authorizationMediator.getSignInWithPhoneFlowDirection())
        }
        appStateViewModel.openPinScreenAction.observe(lifecycle) {
            navigateTo(authorizationMediator.getPinCodeFlowDirection())
        }
        appStateViewModel.openOnBoardingScreenAction.observe(lifecycle) {
            navigateTo(onBoardingMediator.getOnBoardingFlowDirection())
        }
        appStateViewModel.openAppScreenAction.observe(lifecycle) {
            navigateTo(mainMediator.getAFlowDirection())
        }
    }

    private fun navigateTo(direction: NavDirections) {
        findNavController(R.id.fragmentContainer).navigate(direction, resetNavigationOptions)
    }
}