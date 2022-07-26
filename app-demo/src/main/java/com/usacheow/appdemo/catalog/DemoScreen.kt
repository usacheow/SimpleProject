package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.appdemo.DemoDestinations
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.BadgeTileState
import com.usacheow.coreuicompose.uikit.listtile.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.LocalWindowSizeClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current,
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())

    val headerModifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
    val cardModifier = Modifier.padding(8.dp)

    val items = items(
        navController = navController,
        showDialogClickListener = { isDialogVisible = true },
    )

    val modifier = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> Modifier.fillMaxWidth()
        else -> Modifier.width(700.dp)
    }

    val columnsCount = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 2
        else -> 4
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Demo UIkit".textValue(),
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(columnsCount),
                    modifier = modifier
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp)
                        .padding(it),
                    contentPadding = insetAllExcludeTop(),
                ) {
                    items(
                        items = items,
                        span = { item -> if (item is HeaderTileState) GridItemSpan(columnsCount) else GridItemSpan(1) },
                    ) {
                        it.Content(when (it is HeaderTileState) {
                            true -> headerModifier
                            false -> cardModifier
                        })
                    }
                }
            }
        },
    )

    if (isDialogVisible) {
        SampleAlertDialog {
            isDialogVisible = false
        }
    }
}

@Composable
private fun items(
    navController: NavHostController,
    showDialogClickListener: () -> Unit,
) = listOf(
    HeaderTileState.large("Atoms".textValue()),
    BadgeTileState.Data(
        header = "atom".textValue(),
        value = "1. Typography".textValue(),
        onClick = { navController.navigate(DemoDestinations.Typography) }
    ),
    BadgeTileState.Data(
        header = "atom".textValue(),
        value = "2. Palette".textValue(),
        onClick = { navController.navigate(DemoDestinations.Palette) }
    ),
    BadgeTileState.Data(
        header = "atom".textValue(),
        value = "3. Buttons".textValue(),
        onClick = { navController.navigate(DemoDestinations.Buttons) }
    ),
    BadgeTileState.Data(
        header = "atom".textValue(),
        value = "4. Inputs".textValue(),
        onClick = { navController.navigate(DemoDestinations.Inputs) }
    ),

    HeaderTileState.large("Molecules".textValue()),
    BadgeTileState.Data(
        header = "molecule".textValue(),
        value = "1. Cell Tiles".textValue(),
        onClick = { navController.navigate(DemoDestinations.CellTiles) }
    ),
    BadgeTileState.Data(
        header = "molecule".textValue(),
        value = "2. Tag Lists".textValue(),
        onClick = { navController.navigate(DemoDestinations.TagList) }
    ),
    BadgeTileState.Data(
        header = "molecule".textValue(),
        value = "3. Information Tiles".textValue(),
        onClick = { navController.navigate(DemoDestinations.InformationTiles) }
    ),

    HeaderTileState.large("Organisms".textValue()),
    BadgeTileState.Data(
        header = "organism".textValue(),
        value = "1. Message Tiles".textValue(),
        onClick = { navController.navigate(DemoDestinations.Messages) }
    ),
    BadgeTileState.Data(
        header = "organism".textValue(),
        value = "2. Num Pad".textValue(),
        onClick = { navController.navigate(DemoDestinations.NumPad) }
    ),

    HeaderTileState.large("Templates".textValue()),
    BadgeTileState.Data(
        header = "template".textValue(),
        value = "1. Alert Dialog".textValue(),
        onClick = { showDialogClickListener() }
    ),
    BadgeTileState.Data(
        header = "template".textValue(),
        value = "2. Bottom sheet".textValue(),
        onClick = { navController.navigate(DemoDestinations.BottomSheet) }
    ),
    BadgeTileState.Data(
        header = "template".textValue(),
        value = "3. Modal bottom sheet".textValue(),
        onClick = { navController.navigate(DemoDestinations.ModalBottomSheet) }
    ),
)