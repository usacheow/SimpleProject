package com.usacheow.appdemo

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.usacheow.appdemo.databinding.ActivityHostBinding
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.navigation.OPEN_IN
import com.usacheow.coreui.utils.navigation.REPLACING
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.coreui.utils.observe
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : SimpleActivity<ActivityHostBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )
    
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
}