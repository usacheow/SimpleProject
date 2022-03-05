package com.usacheow.appdemo

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsCompat
import com.usacheow.appdemo.databinding.ActivityHostBinding
import com.usacheow.corenavigation.base.passBackPressedTo
import com.usacheow.coreui.screen.SimpleActivity
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.hideIme
import com.usacheow.coreuiview.tools.isImeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : SimpleActivity<ActivityHostBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )

    private var isKeyboardVisible = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                isKeyboardVisible -> windowInsetsController?.hideIme()
                else -> passBackPressedTo(this@DemoActivity)
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