package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.CellTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.appdemo.R as AppDemoR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CellTilesScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Cell tiles".textValue(),
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
        title = "Cell tile title".textValue(),
    ),
    CellTileState.Data(
        subtitle = "Subtitle".textValue(),
        title = "Cell tile title".textValue(),
    ),
    CellTileState.Data(
        subtitle = "Subtitle".textValue(),
        title = "Cell tile title".textValue(),
        additional = "Additional text with some interesting information".textValue(),
    ),
    CellTileState.Data(
        title = "Cell tile title".textValue(),
        value = "Value".textValue(),
    ),
    CellTileState.Data(
        leftPart = CellTileState.LeftPart.Icon(
            icon = AppTheme.specificIcons.account,
            background = IconValue.ResVector(R.drawable.bg_ic_square),
        ),
        subtitle = "Subtitle".textValue(),
        title = "Title".textValue(),
        additional = "Additional".textValue(),
        rightPart = CellTileState.RightPart.Logo(ImageValue.ResImage(AppDemoR.drawable.demo_avatar)),
        onClick = {},
    ),
    CellTileState.Data(
        subtitle = "Subtitle".textValue(),
        title = "Title".textValue(),
        additional = "Additional".textValue(),
        rightPart = CellTileState.RightPart.ActionIcon(AppTheme.specificIcons.navigateNext.toImageValue()),
        onClick = {},
    ),
    CellTileState.Data(
        leftPart = CellTileState.LeftPart.Logo(ImageValue.ResImage(AppDemoR.drawable.demo_avatar)),
        subtitle = "Subtitle".textValue(),
        title = "Title".textValue(),
        additional = "Additional".textValue(),
        rightPart = CellTileState.RightPart.Switch(true),
        onClick = {},
    ),
)