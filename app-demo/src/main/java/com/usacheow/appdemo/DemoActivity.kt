package com.usacheow.appdemo

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.usacheow.appdemo.databinding.ActivityHostBinding
import com.usacheow.appstate.AppStateViewModel
import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.navigation.resetNavOptions
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : SimpleActivity<ActivityHostBinding>() {

    override val params = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )

    private val appStateViewModel by viewModels<AppStateViewModel>()

    private var isKeyboardVisible = false
    private val resetNavigationOptions = resetNavOptions(to = R.id.demo_app_nav_graph)

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
            navigateTo(R.id.sign_in_with_phone_nav_graph)
        }
        appStateViewModel.openPinScreenAction.observe(lifecycle) {
            navigateTo(R.id.pin_code_nav_graph)
        }
        appStateViewModel.openOnBoardingScreenAction.observe(lifecycle) {
            navigateTo(R.id.on_boarding_nav_graph)
        }
        appStateViewModel.openAppScreenAction.observe(lifecycle) {
            navigateTo(R.id.main_nav_graph)
        }
    }

    private fun navigateTo(@IdRes id: Int) {
        findNavController(R.id.fragmentContainer).navigate(FeatureNavDirection(id), resetNavigationOptions)
    }
}