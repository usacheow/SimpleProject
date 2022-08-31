package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.material.color.MaterialColors
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaletteScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Palette".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxHeight(),
            contentPadding = insetAllExcludeTop().asPaddingValues().add(horizontal = 24.dp).add(it),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = items,
                span = { if (it is HeaderTileState) GridItemSpan(2) else GridItemSpan(1) },
            ) {
                it.Content(modifier = Modifier)
            }
        }
    }
}

@Composable
private fun items() = listOf(
    HeaderTileState.Data(value = "Primary".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.primary, "primary"),
    ColorItem(AppTheme.specificColorScheme.onPrimary, "onPrimary"),
    ColorItem(AppTheme.specificColorScheme.primaryContainer, "primaryContainer"),
    ColorItem(AppTheme.specificColorScheme.onPrimaryContainer, "onPrimaryContainer"),
    ColorItem(AppTheme.specificColorScheme.primaryInverse, "primaryInverse"),

    HeaderTileState.Data(value = "Secondary".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.secondary, "secondary"),
    ColorItem(AppTheme.specificColorScheme.onSecondary, "onSecondary"),
    ColorItem(AppTheme.specificColorScheme.secondaryContainer, "secondaryContainer"),
    ColorItem(AppTheme.specificColorScheme.onSecondaryContainer, "onSecondaryContainer"),

    HeaderTileState.Data(value = "Tertiary".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.tertiary, "tertiary"),
    ColorItem(AppTheme.specificColorScheme.onTertiary, "onTertiary"),
    ColorItem(AppTheme.specificColorScheme.tertiaryContainer, "tertiaryContainer"),
    ColorItem(AppTheme.specificColorScheme.onTertiaryContainer, "onTertiaryContainer"),

    HeaderTileState.Data(value = "Error".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.error, "error"),
    ColorItem(AppTheme.specificColorScheme.onError, "onError"),
    ColorItem(AppTheme.specificColorScheme.errorContainer, "errorContainer"),
    ColorItem(AppTheme.specificColorScheme.onErrorContainer, "onErrorContainer"),

    HeaderTileState.Data(value = "Background".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.background, "background"),
    ColorItem(AppTheme.specificColorScheme.onBackground, "onBackground"),

    HeaderTileState.Data(value = "Surface".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.surface, "surface"),
    ColorItem(AppTheme.specificColorScheme.onSurface, "onSurface"),
    ColorItem(AppTheme.specificColorScheme.surfaceVariant, "surfaceVariant"),
    ColorItem(AppTheme.specificColorScheme.onSurfaceVariant, "onSurfaceVariant"),
    ColorItem(AppTheme.specificColorScheme.surfaceInverse, "surfaceInverse"),
    ColorItem(AppTheme.specificColorScheme.onSurfaceInverse, "onSurfaceInverse"),

    HeaderTileState.Data(value = "Text/Icons".textValue(), type = HeaderTileState.Type.Small),
    ColorItem(AppTheme.specificColorScheme.symbolPrimary, "symbolPrimary"),
    ColorItem(AppTheme.specificColorScheme.symbolPrimaryInverse, "symbolPrimaryInverse"),
    ColorItem(AppTheme.specificColorScheme.symbolSecondary, "symbolSecondary"),
    ColorItem(AppTheme.specificColorScheme.symbolSecondaryInverse, "symbolSecondaryInverse"),
    ColorItem(AppTheme.specificColorScheme.symbolTertiary, "symbolTertiary"),
    ColorItem(AppTheme.specificColorScheme.symbolTertiaryInverse, "symbolTertiaryInverse"),
)

@OptIn(ExperimentalMaterial3Api::class)
data class ColorItem(
    val color: Color,
    val name: String,
) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = color,
                contentColor = if (MaterialColors.isColorLight(color.toArgb())) {
                    AppTheme.specificColorScheme.symbolPrimary
                } else {
                    AppTheme.specificColorScheme.symbolPrimaryInverse
                },
            ),
            modifier = modifier.heightIn(min = 100.dp),
            shape = AppTheme.shapes.extraLarge,
        ) {
            Text(
                text = name,
                style = AppTheme.specificTypography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                maxLines = 2,
            )
            Text(
                text = "#%s".format(Integer.toHexString(color.toArgb())).uppercase(),
                style = AppTheme.specificTypography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            )
        }
    }
}