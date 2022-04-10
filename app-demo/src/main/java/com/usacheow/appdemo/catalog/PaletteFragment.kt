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
import com.google.android.material.color.MaterialColors
import com.usacheow.appdemo.DemoRouter
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreui.screen.ComposeFragment
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@AndroidEntryPoint
class PaletteFragment : ComposeFragment() {

    @Inject
    lateinit var router: DemoRouter

    @Composable
    override fun Screen() {
        val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
        val items = items()

        val headerModifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        val cardModifier = Modifier.padding(8.dp)

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = TextValue.Simple("Palette"),
                    navigationIcon = com.usacheow.coreuitheme.R.drawable.ic_back to router::back,
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
                    span = { if(it is HeaderTileState) GridItemSpan(2) else GridItemSpan(1) },
                ) {
                    it.Content(when(it is HeaderTileState) {
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
        ColorItem(AppTheme.commonColors.primary, "primary"),
        ColorItem(AppTheme.commonColors.onPrimary, "onPrimary"),
        ColorItem(AppTheme.commonColors.primaryContainer, "primaryContainer"),
        ColorItem(AppTheme.commonColors.onPrimaryContainer, "onPrimaryContainer"),
        ColorItem(AppTheme.commonColors.primaryInverse, "primaryInverse"),

        HeaderTileState(value = TextValue.Simple("Secondary"), type = HeaderTileState.Type.SmallSecondary),
        ColorItem(AppTheme.commonColors.secondary, "secondary"),
        ColorItem(AppTheme.commonColors.onSecondary, "onSecondary"),
        ColorItem(AppTheme.commonColors.secondaryContainer, "secondaryContainer"),
        ColorItem(AppTheme.commonColors.onSecondaryContainer, "onSecondaryContainer"),

        HeaderTileState(value = TextValue.Simple("Tertiary"), type = HeaderTileState.Type.SmallSecondary),
        ColorItem(AppTheme.commonColors.tertiary, "tertiary"),
        ColorItem(AppTheme.commonColors.onTertiary, "onTertiary"),
        ColorItem(AppTheme.commonColors.tertiaryContainer, "tertiaryContainer"),
        ColorItem(AppTheme.commonColors.onTertiaryContainer, "onTertiaryContainer"),

        HeaderTileState(value = TextValue.Simple("Error"), type = HeaderTileState.Type.SmallSecondary),
        ColorItem(AppTheme.commonColors.error, "error"),
        ColorItem(AppTheme.commonColors.onError, "onError"),
        ColorItem(AppTheme.commonColors.errorContainer, "errorContainer"),
        ColorItem(AppTheme.commonColors.onErrorContainer, "onErrorContainer"),

        HeaderTileState(value = TextValue.Simple("Background"), type = HeaderTileState.Type.SmallSecondary),
        ColorItem(AppTheme.commonColors.background, "background"),
        ColorItem(AppTheme.commonColors.onBackground, "onBackground"),

        HeaderTileState(value = TextValue.Simple("Surface"), type = HeaderTileState.Type.SmallSecondary),
        ColorItem(AppTheme.commonColors.surface, "surface"),
        ColorItem(AppTheme.commonColors.onSurface, "onSurface"),
        ColorItem(AppTheme.commonColors.surfaceVariant, "surfaceVariant"),
        ColorItem(AppTheme.commonColors.onSurfaceVariant, "onSurfaceVariant"),
        ColorItem(AppTheme.commonColors.surfaceInverse, "surfaceInverse"),
        ColorItem(AppTheme.commonColors.onSurfaceInverse, "onSurfaceInverse"),

        HeaderTileState(value = TextValue.Simple("Text/Icons"), type = HeaderTileState.Type.SmallSecondary),
        ColorItem(AppTheme.commonColors.symbolPrimary, "symbolPrimary"),
        ColorItem(AppTheme.commonColors.symbolPrimaryInverse, "symbolPrimaryInverse"),
        ColorItem(AppTheme.commonColors.symbolSecondary, "symbolSecondary"),
        ColorItem(AppTheme.commonColors.symbolSecondaryInverse, "symbolSecondaryInverse"),
        ColorItem(AppTheme.commonColors.symbolTertiary, "symbolTertiary"),
        ColorItem(AppTheme.commonColors.symbolTertiaryInverse, "symbolTertiaryInverse"),
    )
}

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
                AppTheme.commonColors.symbolPrimary
            } else {
                AppTheme.commonColors.symbolPrimaryInverse
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