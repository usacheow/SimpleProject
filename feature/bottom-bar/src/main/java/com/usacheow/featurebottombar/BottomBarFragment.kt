package com.usacheow.featurebottombar

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.OnBackPressedCallback
import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import androidx.annotation.RequiresApi
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.usacheow.corenavigation.base.passBackPressedTo
import com.usacheow.coreui.screen.SimpleFragment
import com.usacheow.coreuiview.tools.PaddingValue
import com.usacheow.coreuiview.tools.applyBottomInset
import com.usacheow.coreuiview.tools.doOnApplyWindowInsets
import com.usacheow.coreuiview.tools.getBottomInset
import com.usacheow.coreuiview.tools.isImeVisible
import com.usacheow.corenavigation.base.addArgs
import com.usacheow.corenavigation.base.getArgs
import com.usacheow.featurebottombar.databinding.FragmentBottomBarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import kotlin.math.max
import com.usacheow.featurebottombar.R as FeatureR

private const val ROOT_DESTINATIONS_COUNT = 1

@AndroidEntryPoint
class BottomBarFragment : SimpleFragment<FragmentBottomBarBinding>() {

    override val defaultParams = Params(
        viewBindingProvider = FragmentBottomBarBinding::inflate,
    )

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val destinationList = navController.currentDestination?.hierarchy
                ?.toList()
                ?.asReversed()
                ?.drop(ROOT_DESTINATIONS_COUNT)
            val startDestinationInSelectedTab = destinationList?.first() as? NavGraph?
            val currentDestinationInSelectedTab = destinationList?.last()
            val selectedTabId = binding.appBottomBar.selectedItemId
            val firstTabId = binding.appBottomBar.menu.getItem(0).itemId

            val isCurrentDestinationSameAsStart = startDestinationInSelectedTab == currentDestinationInSelectedTab
            val isCurrentDestinationStartInNestedGraph =
                startDestinationInSelectedTab?.startDestinationId == currentDestinationInSelectedTab?.id
            val needSelectFirstTab = (isCurrentDestinationSameAsStart || isCurrentDestinationStartInNestedGraph)
                    && selectedTabId != firstTabId
                    && startDestinationInSelectedTab?.id == selectedTabId

            when {
                needSelectFirstTab -> binding.appBottomBar.selectedItemId = firstTabId
                !navController.navigateUp() -> passBackPressedTo(requireActivity())
            }
        }
    }

    private var isKeyboardVisible = false
    private val navController by lazy {
        (childFragmentManager.findFragmentById(FeatureR.id.bottomBarContainerLayout) as NavHostFragment).navController
    }

    companion object {
        fun bundle(
            @MenuRes menuRes: Int,
            @NavigationRes graphRes: Int,
        ) = Bundle().addArgs(BottomBarArgs(menuRes, graphRes))
    }

    override fun onApplyWindowInsets(insets: WindowInsetsCompat, padding: PaddingValue): WindowInsetsCompat {
        val newBottomBarBottomPaddingPx = insets.getBottomInset()
        val appBottomBarHeight = binding.appBottomBar.height + when (binding.appBottomBar.paddingBottom) {
            0 -> newBottomBarBottomPaddingPx
            else -> 0
        }

        binding.appBottomBar.applyBottomInset(newBottomBarBottomPaddingPx)
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
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        arguments?.getArgs<BottomBarArgs>()?.let {
            binding.appBottomBar.menu.clear()
            binding.appBottomBar.inflateMenu(it.menuRes)
            navController.graph = navController.navInflater.inflate(it.graphRes)
        }

        binding.appBottomBar.setupWithNavController(navController)
        binding.appBottomBar.doOnApplyWindowInsets { insets, _ -> insets }
    }
}

@Parcelize
data class BottomBarArgs(
    @MenuRes val menuRes: Int,
    @NavigationRes val graphRes: Int,
) : Parcelable