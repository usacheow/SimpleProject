@file:OptIn(ExperimentalMaterial3Api::class)

package com.usacheow.coreuicompose.uikit.duplicate

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuitheme.compose.AppTheme

object SimpleTopAppBarConfig {

    @Composable
    fun surfaceColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surface,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurface,
        titleContentColor = AppTheme.specificColorScheme.onSurface,
        actionIconContentColor = AppTheme.specificColorScheme.onSurface,
    )

    @Composable
    fun surfaceVariantColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surfaceVariant,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurfaceVariant,
        titleContentColor = AppTheme.specificColorScheme.onSurfaceVariant,
        actionIconContentColor = AppTheme.specificColorScheme.onSurfaceVariant,
    )
}

@Composable
fun SimpleTopAppBar(
    title: TextValue?,
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    windowInsets: WindowInsets = insetAllExcludeBottom(),
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior?,
) {
    CenterAlignedTopAppBar(
        title = { title?.get()?.let { Text(it, style = titleTextStyle) } },
        navigationIcon = {
            if (navigationIcon != null) {
                TopAppBarNavIcon(
                    iconValue = navigationIcon.first,
                    onClick = { navigationIcon.second() },
                )
            }
        },
        actions = {
            Row {
                actions.toList().forEach {
                    TopAppBarAction(data = it)
                }
            }
        },
        colors = colors,
        scrollBehavior = scrollBehavior,
        windowInsets = windowInsets,
    )
}