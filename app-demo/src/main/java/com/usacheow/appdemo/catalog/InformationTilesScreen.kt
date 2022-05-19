package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.BadgeTileState
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationTilesScreen(navController: NavHostController) {
    val badges = badges()
    val smallBadges = smallBadges()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Information tiles"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            contentPadding = insetAllExcludeTop(),
        ) {
            items(large()) {
                it.Content(modifier = Modifier.padding(8.dp))
            }
            items(medium()) {
                it.Content(modifier = Modifier.padding(8.dp))
            }
            items(small()) {
                it.Content(modifier = Modifier.padding(8.dp))
            }
            item {
                LazyRow {
                    items(badges) {
                        it.Content(Modifier.padding(8.dp))
                    }
                }
            }
            item {
                LazyRow {
                    items(smallBadges) {
                        it.Content(Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

private fun large(): List<WidgetState> = listOf(
    HeaderTileState(
        value = TextValue.Simple("Large title"),
        type = HeaderTileState.Type.LargePrimary,
    ),
    HeaderTileState(
        value = TextValue.Simple("Large title"),
        type = HeaderTileState.Type.LargeSecondary,
        action = HeaderTileState.Action.Text(TextValue.Simple("action")),
        clickListener = {},
    ),
    HeaderTileState.shimmer(HeaderTileState.Type.LargePrimary),
)

private fun medium(): List<WidgetState> = listOf(
    HeaderTileState(
        value = TextValue.Simple("Medium title"),
        type = HeaderTileState.Type.MediumPrimary,
    ),
    HeaderTileState(
        value = TextValue.Simple("Medium title"),
        type = HeaderTileState.Type.MediumSecondary,
        action = HeaderTileState.Action.Text(TextValue.Simple("action")),
        clickListener = {},
    ),
    HeaderTileState.shimmer(HeaderTileState.Type.MediumPrimary),
)

private fun small(): List<WidgetState> = listOf(
    HeaderTileState(
        value = TextValue.Simple("Small title"),
        type = HeaderTileState.Type.SmallPrimary,
    ),
    HeaderTileState(
        value = TextValue.Simple("Small title"),
        type = HeaderTileState.Type.SmallSecondary,
        action = HeaderTileState.Action.Text(TextValue.Simple("action")),
        clickListener = {},
    ),
    HeaderTileState.shimmer(HeaderTileState.Type.SmallPrimary),
)

@Composable
private fun badges(): List<WidgetState> = listOf(
    BadgeTileState.shimmer(),
    BadgeTileState(
        header = TextValue.Simple("Badge tile header text"),
        value = TextValue.Simple("Badge tile text"),
        contentColor = AppTheme.specificColorScheme.onSurface,
        containerColor = AppTheme.specificColorScheme.surface,
        clickListener = {}
    ),
)

@Composable
private fun smallBadges(): List<WidgetState> = listOf(
    BadgeTileState.shimmer(hasHeader = false),
    BadgeTileState(
        value = TextValue.Simple("Badge tile text"),
        contentColor = AppTheme.specificColorScheme.onSurface,
        containerColor = AppTheme.specificColorScheme.surface,
        clickListener = {}
    ),
)