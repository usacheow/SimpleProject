package com.usacheow.appdemo.catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.material.color.MaterialColors
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PaletteScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val items = items()

    val headerModifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
    val cardModifier = Modifier.padding(8.dp)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Palette"),
                navigationIcon = com.usacheow.coreuitheme.R.drawable.ic_back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            contentPadding = getBottomInset(),
        ) {
            items(
                items = items,
                span = { if (it is HeaderTileState) GridItemSpan(2) else GridItemSpan(1) },
            ) {
                it.Content(when (it is HeaderTileState) {
                    true -> headerModifier
                    false -> cardModifier
                })
            }
        }
    }
}

@Composable
private fun items() = listOf(
    HeaderTileState(value = TextValue.Simple("Primary"), type = HeaderTileState.Type.SmallSecondary),
    ColorItem(AppTheme.specificColorScheme.primary, "primary"),
    ColorItem(AppTheme.specificColorScheme.onPrimary, "onPrimary"),
    ColorItem(AppTheme.specificColorScheme.primaryContainer, "primaryContainer"),
    ColorItem(AppTheme.specificColorScheme.onPrimaryContainer, "onPrimaryContainer"),
    ColorItem(AppTheme.specificColorScheme.primaryInverse, "primaryInverse"),

    HeaderTileState(value = TextValue.Simple("Secondary"), type = HeaderTileState.Type.SmallSecondary),
    ColorItem(AppTheme.specificColorScheme.secondary, "secondary"),
    ColorItem(AppTheme.specificColorScheme.onSecondary, "onSecondary"),
    ColorItem(AppTheme.specificColorScheme.secondaryContainer, "secondaryContainer"),
    ColorItem(AppTheme.specificColorScheme.onSecondaryContainer, "onSecondaryContainer"),

    HeaderTileState(value = TextValue.Simple("Tertiary"), type = HeaderTileState.Type.SmallSecondary),
    ColorItem(AppTheme.specificColorScheme.tertiary, "tertiary"),
    ColorItem(AppTheme.specificColorScheme.onTertiary, "onTertiary"),
    ColorItem(AppTheme.specificColorScheme.tertiaryContainer, "tertiaryContainer"),
    ColorItem(AppTheme.specificColorScheme.onTertiaryContainer, "onTertiaryContainer"),

    HeaderTileState(value = TextValue.Simple("Error"), type = HeaderTileState.Type.SmallSecondary),
    ColorItem(AppTheme.specificColorScheme.error, "error"),
    ColorItem(AppTheme.specificColorScheme.onError, "onError"),
    ColorItem(AppTheme.specificColorScheme.errorContainer, "errorContainer"),
    ColorItem(AppTheme.specificColorScheme.onErrorContainer, "onErrorContainer"),

    HeaderTileState(value = TextValue.Simple("Background"), type = HeaderTileState.Type.SmallSecondary),
    ColorItem(AppTheme.specificColorScheme.background, "background"),
    ColorItem(AppTheme.specificColorScheme.onBackground, "onBackground"),

    HeaderTileState(value = TextValue.Simple("Surface"), type = HeaderTileState.Type.SmallSecondary),
    ColorItem(AppTheme.specificColorScheme.surface, "surface"),
    ColorItem(AppTheme.specificColorScheme.onSurface, "onSurface"),
    ColorItem(AppTheme.specificColorScheme.surfaceVariant, "surfaceVariant"),
    ColorItem(AppTheme.specificColorScheme.onSurfaceVariant, "onSurfaceVariant"),
    ColorItem(AppTheme.specificColorScheme.surfaceInverse, "surfaceInverse"),
    ColorItem(AppTheme.specificColorScheme.onSurfaceInverse, "onSurfaceInverse"),

    HeaderTileState(value = TextValue.Simple("Text/Icons"), type = HeaderTileState.Type.SmallSecondary),
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
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        OutlinedCard(
            containerColor = color,
            contentColor = if (MaterialColors.isColorLight(color.toArgb())) {
                AppTheme.specificColorScheme.symbolPrimary
            } else {
                AppTheme.specificColorScheme.symbolPrimaryInverse
            },
            modifier = modifier.heightIn(min = 100.dp),
            shape = AppTheme.shapes.extraLarge,
        ) {
            Text(
                text = name,
                style = AppTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                maxLines = 2,
            )
            Text(
                text = "#%s".format(Integer.toHexString(color.toArgb())).uppercase(),
                style = AppTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            )
        }
    }
}