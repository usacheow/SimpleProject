package com.usacheow.apptest

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowInsetsCompat
import com.usacheow.apptest.databinding.ActivityHostBinding
import com.usacheow.coreui.navigation.passBackPressedTo
import com.usacheow.coreui.screen.SimpleActivity
import com.usacheow.coreui.uikit.helper.PaddingValue
import com.usacheow.coreui.uikit.helper.hideIme
import com.usacheow.coreui.uikit.helper.isImeVisible
import dagger.hilt.android.AndroidEntryPoint
import com.usacheow.coreui.R as TestAppR

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
        setTheme(TestAppR.style.Simple_AppTheme)
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}