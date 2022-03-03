package com.usacheow.apptest

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowInsetsCompat
import com.usacheow.apptest.databinding.ActivityHostBinding
import com.usacheow.corenavigation.base.passBackPressedTo
import com.usacheow.coreui.screen.SimpleActivity
import com.usacheow.coreuiview.helper.PaddingValue
import com.usacheow.coreuiview.helper.hideIme
import com.usacheow.coreuiview.helper.isImeVisible
import dagger.hilt.android.AndroidEntryPoint
import com.usacheow.coreuitheme.R as CoreUiThemeR

@AndroidEntryPoint
class TestActivity : SimpleActivity<ActivityHostBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = ActivityHostBinding::inflate,
    )

    private var isKeyboardVisible = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                isKeyboardVisible -> windowInsetsController?.hideIme()
                else -> passBackPressedTo(this@TestActivity)
            }
        }
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        isKeyboardVisible = insets.isImeVisible()
        return insets
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(CoreUiThemeR.style.Simple_AppTheme)
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}