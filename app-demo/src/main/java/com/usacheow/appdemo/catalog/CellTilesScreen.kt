package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.CellTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.appdemo.R as AppDemoR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CellTilesScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Cell tiles"),
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
            items(items) {
                it.Content(Modifier)
            }
        }
    }
}

@Composable
private fun items(): List<TileState> = listOf(
    CellTileState.Shimmer,
    CellTileState.Data(
        title = TextValue.Simple("Cell tile title"),
    ),
    CellTileState.Data(
        subtitle = TextValue.Simple("Subtitle"),
        title = TextValue.Simple("Cell tile title"),
    ),
    CellTileState.Data(
        subtitle = TextValue.Simple("Subtitle"),
        title = TextValue.Simple("Cell tile title"),
        additional = TextValue.Simple("Additional text with some interesting information"),
    ),
    CellTileState.Data(
        title = TextValue.Simple("Cell tile title"),
        value = TextValue.Simple("Value"),
    ),
    CellTileState.Data(
        leftPart = CellTileState.LeftPart.Icon(
            icon = AppTheme.specificIcons.account,
            background = IconValue.ResVector(R.drawable.bg_ic_square),
        ),
        subtitle = TextValue.Simple("Subtitle"),
        title = TextValue.Simple("Title"),
        additional = TextValue.Simple("Additional"),
        rightPart = CellTileState.RightPart.Logo(ImageValue.ResImage(AppDemoR.drawable.demo_avatar)),
        clickListener = {},
    ),
    CellTileState.Data(
        subtitle = TextValue.Simple("Subtitle"),
        title = TextValue.Simple("Title"),
        additional = TextValue.Simple("Additional"),
        rightPart = CellTileState.RightPart.ActionIcon(AppTheme.specificIcons.navigateNext.toImageValue()),
        clickListener = {},
    ),
    CellTileState.Data(
        leftPart = CellTileState.LeftPart.Logo(ImageValue.ResImage(AppDemoR.drawable.demo_avatar)),
        subtitle = TextValue.Simple("Subtitle"),
        title = TextValue.Simple("Title"),
        additional = TextValue.Simple("Additional"),
        rightPart = CellTileState.RightPart.Switch(true),
        clickListener = {},
    ),
)