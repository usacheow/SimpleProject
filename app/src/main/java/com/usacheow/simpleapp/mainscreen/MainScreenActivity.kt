package com.usacheow.simpleapp.mainscreen

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.BottomBarMediator
import com.usacheow.coremediator.MainMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.navigation.passBackPressedTo
import com.usacheow.coreui.screen.SimpleActivity
import com.usacheow.coreui.utils.navigation.OPEN_IN
import com.usacheow.coreui.utils.navigation.REPLACING
import com.usacheow.coreui.viewmodel.observe
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.hideIme
import com.usacheow.coreui.uikit.helper.isImeVisible
import com.usacheow.simpleapp.databinding.ActivityHostBinding
import com.usacheow.simpleapp.mainscreen.MainScreenViewModel.Action
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.usacheow.simpleapp.R as AppR

@AndroidEntryPoint
class MainScreenActivity : SimpleActivity<ActivityHostBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )

    @Inject lateinit var authMediator: AuthorizationMediator
    @Inject lateinit var onBoardingMediator: OnBoardingMediator
    @Inject lateinit var mainMediator: MainMediator
    @Inject lateinit var bottomBarMediator: BottomBarMediator

    private val viewModel by viewModels<MainScreenViewModel>()

    private var isKeyboardVisible = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                isKeyboardVisible -> windowInsetsController?.hideIme()
                else -> passBackPressedTo(this@MainScreenActivity)
            }
        }
    }

    override fun findNavController() = findNavController(AppR.id.fragmentContainer)

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
        val mainAppFlowDirection = bottomBarMediator.getBottomBarFlowDirection(
            AppR.menu.m_bottom_bar,
            AppR.navigation.auth_zone_nav_graph,
        )
        val nextDirection = mainAppFlowDirection REPLACING AppR.id.app_nav_graph

        viewModel.action.observe(this) {
            val direction = when (it) {
                is Action.OpenOnBoardingScreen -> onBoardingMediator.getOnBoardingFlowDirection(it.args, nextDirection)
                is Action.OpenAuthScreen -> authMediator.getSignInWithPhoneFlowDirection(nextDirection)
                is Action.OpenPinScreen -> authMediator.getPinCodeFlowDirection(nextDirection)
                is Action.OpenAppScreen -> mainAppFlowDirection
            }

            direction REPLACING AppR.id.app_nav_graph OPEN_IN findNavController()
        }
    }
}