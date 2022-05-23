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
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.BadgeTileState
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.LocalWindowSizeClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current,
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

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
                title = TextValue.Simple("Demo UIkit"),
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
    HeaderTileState.largePrimary(TextValue.Simple("Atoms")),
    BadgeTileState.Data(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("1. Typography"),
        clickListener = { navController.navigate(DemoDestinations.Typography) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("2. Palette"),
        clickListener = { navController.navigate(DemoDestinations.Palette) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("3. Buttons"),
        clickListener = { navController.navigate(DemoDestinations.Buttons) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("4. Inputs"),
        clickListener = { navController.navigate(DemoDestinations.Inputs) }
    ),

    HeaderTileState.largePrimary(TextValue.Simple("Molecules")),
    BadgeTileState.Data(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("1. Cell Tiles"),
        clickListener = { navController.navigate(DemoDestinations.CellTiles) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("2. Tag Lists"),
        clickListener = { navController.navigate(DemoDestinations.TagList) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("3. Information Tiles"),
        clickListener = { navController.navigate(DemoDestinations.InformationTiles) }
    ),

    HeaderTileState.largePrimary(TextValue.Simple("Organisms")),
    BadgeTileState.Data(
        header = TextValue.Simple("organism"),
        value = TextValue.Simple("1. Message Tiles"),
        clickListener = { navController.navigate(DemoDestinations.Messages) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("organism"),
        value = TextValue.Simple("2. Num Pad"),
        clickListener = { navController.navigate(DemoDestinations.NumPad) }
    ),

    HeaderTileState.largePrimary(TextValue.Simple("Templates")),
    BadgeTileState.Data(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("1. Alert Dialog"),
        clickListener = { showDialogClickListener() }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("2. Bottom sheet"),
        clickListener = { navController.navigate(DemoDestinations.BottomSheet) }
    ),
    BadgeTileState.Data(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("3. Modal bottom sheet"),
        clickListener = { navController.navigate(DemoDestinations.ModalBottomSheet) }
    ),
)