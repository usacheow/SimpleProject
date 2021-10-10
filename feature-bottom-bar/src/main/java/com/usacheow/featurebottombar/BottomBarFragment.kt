package com.usacheow.featurebottombar

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import androidx.annotation.RequiresApi
import androidx.core.graphics.Insets
import androidx.core.os.bundleOf
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.usacheow.coreui.fragment.SimpleFragment
import com.usacheow.coreui.utils.view.PaddingValue
import com.usacheow.coreui.utils.view.doOnApplyWindowInsets
import com.usacheow.coreui.utils.view.getBottomInset
import com.usacheow.coreui.utils.view.isImeVisible
import com.usacheow.featurebottombar.databinding.FragmentBottomBarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import kotlin.math.max

private const val ARGS_KEY = "ARGS_KEY"

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment<FragmentBottomBarBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentBottomBarBinding::inflate,
    )

    private var isKeyboardVisible = false
    private val navController by lazy {
        (childFragmentManager.findFragmentById(R.id.bottomBarContainerLayout) as NavHostFragment).navController
    }

    companion object {
        fun bundle(
            @MenuRes menuRes: Int,
            @NavigationRes graphRes: Int,
        ) = bundleOf(ARGS_KEY to BottomBarArgs(menuRes, graphRes))
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        val newBottomBarBottomPaddingPx = insets.getBottomInset()
        val appBottomBarHeight = binding.appBottomBar.height + when (binding.appBottomBar.paddingBottom) {
            0 -> newBottomBarBottomPaddingPx
            else -> 0
        }

        binding.appBottomBar.updatePadding(bottom = newBottomBarBottomPaddingPx)
        isKeyboardVisible = insets.isImeVisible()

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getNewInsetsForApi30(insets, appBottomBarHeight)
        } else {
            getNewInsets(insets, appBottomBarHeight)
        }
    }

    private fun getNewInsets(insets: WindowInsetsCompat, appBottomBarHeight: Int): WindowInsetsCompat {
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
            max(insets.getInsets(WindowInsetsCompat.Type.ime()).bottom - appBottomBarHeight, 0),
        )
        return WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.systemBars(), sbInset)
            .setInsets(WindowInsetsCompat.Type.ime(), imeInset)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getNewInsetsForApi30(insets: WindowInsetsCompat, appBottomBarHeight: Int): WindowInsetsCompat {
        val systemBarsInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
            insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
            appBottomBarHeight,
        )
        val imeInset = Insets.of(
            insets.getInsets(WindowInsetsCompat.Type.ime()).left,
            insets.getInsets(WindowInsetsCompat.Type.ime()).top,
            insets.getInsets(WindowInsetsCompat.Type.ime()).right,
            max(appBottomBarHeight, insets.getInsets(WindowInsetsCompat.Type.ime()).bottom),
        )

        return WindowInsetsCompat.Builder(insets)
            .setInsets(WindowInsetsCompat.Type.systemBars(), systemBarsInset)
            .setInsets(WindowInsetsCompat.Type.ime(), imeInset)
            .build()
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        val args = arguments?.getParcelable<BottomBarArgs>(ARGS_KEY) ?: return

        binding.appBottomBar.menu.clear()
        binding.appBottomBar.inflateMenu(args.menuRes)
        navController.graph = navController.navInflater.inflate(args.graphRes)

        binding.appBottomBar.setupWithNavController(navController)
        binding.appBottomBar.doOnApplyWindowInsets { insets, _ -> insets }
    }
}

@Parcelize
data class BottomBarArgs(
    @MenuRes val menuRes: Int,
    @NavigationRes val graphRes: Int,
) : Parcelable