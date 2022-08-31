package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
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
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.listtile.BadgeTileState
import com.usacheow.coreuicompose.uikit.status.SimpleAlertDialogUi
import com.usacheow.coreuitheme.compose.LocalWindowSizeClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current,
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

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
                title = "UiKit".textValue(),
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
                    contentPadding = insetAllExcludeTop().asPaddingValues().add(it).add(horizontal = 24.dp, vertical = 8.dp),
                    modifier = modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(
                        navController = navController,
                        showDialogClickListener = { isDialogVisible = true },
                        maxSpanCount = columnsCount,
                    )
                }
            }
        },
    )

    if (isDialogVisible) {
        SimpleAlertDialogUi(
            text = "Material dialog example".textValue(),
            buttonText = "Ok".textValue(),
            onClickRequest = { isDialogVisible = false },
        )
    }
}

private fun LazyGridScope.items(
    navController: NavHostController,
    showDialogClickListener: () -> Unit,
    maxSpanCount: Int
) {
    item {
        BadgeTileState.Data(
            header = "theme".textValue(),
            value = "1. Typography".textValue(),
            onClick = { navController.navigate(DemoDestinations.Typography) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "theme".textValue(),
            value = "2. Palette".textValue(),
            onClick = { navController.navigate(DemoDestinations.Palette) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "theme".textValue(),
            value = "3. Settings".textValue(),
            onClick = { navController.navigate(DemoDestinations.Settings) }
        ).Content(modifier = Modifier)
    }

    item(span = { GridItemSpan(maxSpanCount) }) { Spacer(modifier = Modifier.height(16.dp)) }
    item {
        BadgeTileState.Data(
            header = "components".textValue(),
            value = "1. Buttons".textValue(),
            onClick = { navController.navigate(DemoDestinations.Buttons) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "components".textValue(),
            value = "2. Inputs".textValue(),
            onClick = { navController.navigate(DemoDestinations.Inputs) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "tiles".textValue(),
            value = "3. Cell Tiles".textValue(),
            onClick = { navController.navigate(DemoDestinations.CellTiles) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "tiles".textValue(),
            value = "4. Tag Lists".textValue(),
            onClick = { navController.navigate(DemoDestinations.TagList) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "tiles".textValue(),
            value = "5. Information Tiles".textValue(),
            onClick = { navController.navigate(DemoDestinations.InformationTiles) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "tiles".textValue(),
            value = "6. Message Tiles".textValue(),
            onClick = { navController.navigate(DemoDestinations.Messages) }
        ).Content(modifier = Modifier)
    }

    item(span = { GridItemSpan(maxSpanCount) }) { Spacer(modifier = Modifier.height(16.dp)) }
    item {
        BadgeTileState.Data(
            header = "ui".textValue(),
            value = "1. Num Pad".textValue(),
            onClick = { navController.navigate(DemoDestinations.NumPad) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "ui".textValue(),
            value = "2. Alert Dialog".textValue(),
            onClick = { showDialogClickListener() }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "ui".textValue(),
            value = "3. Bottom sheet".textValue(),
            onClick = { navController.navigate(DemoDestinations.BottomSheet) }
        ).Content(modifier = Modifier)
    }
    item {
        BadgeTileState.Data(
            header = "ui".textValue(),
            value = "4. Modal bottom sheet".textValue(),
            onClick = { navController.navigate(DemoDestinations.ModalBottomSheet) }
        ).Content(modifier = Modifier)
    }
}