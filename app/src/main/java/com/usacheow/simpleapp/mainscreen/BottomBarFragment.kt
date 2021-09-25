package com.usacheow.simpleapp.mainscreen

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.isImeVisible
import com.usacheow.simpleapp.R
import com.usacheow.simpleapp.databinding.FragmentBottomBarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment<FragmentBottomBarBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentBottomBarBinding::inflate,
    )

    private var isKeyboardVisible = false
    private val navController by lazy {
        (childFragmentManager.findFragmentById(R.id.bottomBarContainerLayout) as NavHostFragment).navController
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        binding.appBottomBar.updatePadding(bottom = insets.getBottomInset())
        isKeyboardVisible = insets.isImeVisible()

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getNewInsetsForApi30(insets)
        } else {
            getNewInsets(insets)
        }
    }

    private fun getNewInsets(insets: WindowInsetsCompat): WindowInsetsCompat {
        val sbInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
            0,
        )
        val imeInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.ime()).left,
            insets.getInsets(WindowInsetsCompat.Type.ime()).top,
            insets.getInsets(WindowInsetsCompat.Type.ime()).right,
            max(insets.getInsets(WindowInsetsCompat.Type.ime()).bottom - binding.appBottomBar.height, 0),
        )
        return WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.systemBars(), sbInset)
            .setInsets(WindowInsetsCompat.Type.ime(), imeInset)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getNewInsetsForApi30(insets: WindowInsetsCompat): WindowInsetsCompat {
        val systemBarsInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
            binding.appBottomBar.height,
        )
        val imeInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.ime()).left,
            insets.getInsets(WindowInsetsCompat.Type.ime()).top,
            insets.getInsets(WindowInsetsCompat.Type.ime()).right,
            max(binding.appBottomBar.height, insets.getInsets(WindowInsetsCompat.Type.ime()).bottom),
        )

        return WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.systemBars(), systemBarsInset)
            .setInsets(WindowInsetsCompat.Type.ime(), imeInset)
            .build()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        binding.appBottomBar.setupWithNavController(navController)
        binding.appBottomBar.doOnApplyWindowInsets { insets, _ -> insets }
    }
}