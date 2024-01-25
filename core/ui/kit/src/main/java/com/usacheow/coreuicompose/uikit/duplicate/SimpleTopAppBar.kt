package com.usacheow.coreuicompose.uikit.duplicate

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuitheme.compose.AppTheme

object SimpleTopAppBarConfig {

    val ContainerColorAlpha = .9f

    @Composable
    fun surfaceColors() = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surface1,
        scrolledContainerColor = AppTheme.specificColorScheme.surface2,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurface1,
        titleContentColor = AppTheme.specificColorScheme.onSurface1,
        actionIconContentColor = AppTheme.specificColorScheme.onSurface1,
    )

    @Composable
    fun transparentSurfaceColors() = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surface1.copy(alpha = ContainerColorAlpha),
        scrolledContainerColor = AppTheme.specificColorScheme.surface1,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurface1,
        titleContentColor = AppTheme.specificColorScheme.onSurface1,
        actionIconContentColor = AppTheme.specificColorScheme.onSurface1,
    )

    @Composable
    fun surfaceVariantColors() = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surface2,
        scrolledContainerColor = AppTheme.specificColorScheme.surface2,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurface2,
        titleContentColor = AppTheme.specificColorScheme.onSurface2,
        actionIconContentColor = AppTheme.specificColorScheme.onSurface2,
    )

    @Composable
    fun transparentColors() = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.transparent,
        scrolledContainerColor = AppTheme.specificColorScheme.transparent,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurface1,
        titleContentColor = AppTheme.specificColorScheme.onSurface1,
        actionIconContentColor = AppTheme.specificColorScheme.onSurface1,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: TextValue?,
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    windowInsets: WindowInsets = insetAllExcludeBottom(),
    colors: SimpleTopAppBarColors = SimpleTopAppBarConfig.transparentSurfaceColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    additional: @Composable () -> Unit = {},
) {
    SimpleTopAppBar(
        title = { title?.get()?.let { Text(it) } },
        titleTextStyle = titleTextStyle,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
        additional = additional,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    windowInsets: WindowInsets = insetAllExcludeBottom(),
    colors: SimpleTopAppBarColors = SimpleTopAppBarConfig.transparentSurfaceColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    additional: @Composable () -> Unit = {},
) {
    SingleRowTopAppBar(
        title = title,
        titleTextStyle = titleTextStyle,
        navigationIcon = {
            if (navigationIcon != null) {
                TopAppBarNavIcon(
                    iconValue = navigationIcon.first,
                    onClick = { navigationIcon.second() },
                )
            }
        },
        actions = {
            if (actions.toList().isNotEmpty()) Row {
                actions.toList().forEach {
                    TopAppBarActionIcon(data = it)
                }
            }
        },
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
        additional = additional,
    )
}