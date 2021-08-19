package com.usacheow.apptest

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowInsetsCompat
import com.usacheow.apptest.databinding.ActivityHostBinding
import com.usacheow.coreui.R
import com.usacheow.coreui.activity.SimpleActivity
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.hideIme
import com.usacheow.coreui.utils.view.isImeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : SimpleActivity<ActivityHostBinding>() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}