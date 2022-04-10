package com.usacheow.coreui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.SystemBarsIconsColor

abstract class ComposeFragment : Fragment() {

    protected open val defaultParams: Params = Params()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    SystemBarsIconsColor(
                        needWhiteStatusIcons = defaultParams.needWhiteStatusIcons,
                        needWhiteNavigationIcons = defaultParams.needWhiteNavigationIcons,
                    )
                    Screen()
                }
            }
        }
    }

    @Composable
    abstract fun Screen()

    data class Params(
        var needWhiteAllIcons: Boolean = false,
        var needWhiteStatusIcons: Boolean = needWhiteAllIcons,
        var needWhiteNavigationIcons: Boolean = needWhiteAllIcons,
    )
}