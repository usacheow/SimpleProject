package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.BadgeTileState
import com.usacheow.coreuicompose.uikit.listtile.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationTilesScreen(navController: NavHostController) {
    val badges = badges()
    val smallBadges = smallBadges()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Information tiles".textValue(),
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

private fun large(): List<TileState> = listOf(
    HeaderTileState.Data(
        value = "Large title".textValue(),
        type = HeaderTileState.Type.Large,
    ),
    HeaderTileState.Data(
        value = "Large title".textValue(),
        type = HeaderTileState.Type.Large,
        action = HeaderTileState.Action.Text("action".textValue(), onClick = {}),
    ),
    HeaderTileState.Shimmer(HeaderTileState.Type.Large),
)

private fun medium(): List<TileState> = listOf(
    HeaderTileState.Data(
        value = "Medium title".textValue(),
        type = HeaderTileState.Type.Medium,
    ),
    HeaderTileState.Data(
        value = "Medium title".textValue(),
        type = HeaderTileState.Type.Medium,
        action = HeaderTileState.Action.Text("action".textValue(), onClick = {}),
    ),
    HeaderTileState.Shimmer(HeaderTileState.Type.Medium),
)

private fun small(): List<TileState> = listOf(
    HeaderTileState.Data(
        value = "Small title".textValue(),
        type = HeaderTileState.Type.Small,
    ),
    HeaderTileState.Data(
        value = "Small title".textValue(),
        type = HeaderTileState.Type.Small,
        action = HeaderTileState.Action.Text("action".textValue(), onClick = {}),
    ),
    HeaderTileState.Shimmer(HeaderTileState.Type.Small),
)

@Composable
private fun badges(): List<TileState> = listOf(
    BadgeTileState.Shimmer(),
    BadgeTileState.Data(
        header = "Badge tile header text".textValue(),
        value = "Badge tile text".textValue(),
        contentColor = AppTheme.specificColorScheme.onSurface,
        containerColor = AppTheme.specificColorScheme.surface,
        onClick = {}
    ),
)

@Composable
private fun smallBadges(): List<TileState> = listOf(
    BadgeTileState.Shimmer(hasHeader = false),
    BadgeTileState.Data(
        value = "Badge tile text".textValue(),
        contentColor = AppTheme.specificColorScheme.onSurface,
        containerColor = AppTheme.specificColorScheme.surface,
        onClick = {}
    ),
)