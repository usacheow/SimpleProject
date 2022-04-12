package com.usacheow.appdemo

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.appdemo.catalog.SampleAlertDialog
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.BadgeTileState
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DemoScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var isDialogVisible by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = AppTheme.shapes.large,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Modal", style = AppTheme.typography.displayLarge)
                Text(text = "bottom", style = AppTheme.typography.displayLarge)
                Text(text = "sheet", style = AppTheme.typography.displayLarge)
                Text(text = "example", style = AppTheme.typography.displayLarge)
            }
        },
        content = {
            ModalBottomSheetLayoutContent(
                navController = navController,
                showDialogClickListener = { isDialogVisible = true },
                showHideModalBottomSheetClickListener = {
                    coroutineScope.launch {
                        if (modalBottomSheetState.isVisible) {
                            modalBottomSheetState.hide()
                        } else {
                            modalBottomSheetState.show()
                        }
                    }
                },
            )
        }
    )

    if (isDialogVisible) {
        SampleAlertDialog {
            isDialogVisible = false
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ModalBottomSheetLayoutContent(
    navController: NavHostController,
    showDialogClickListener: () -> Unit,
    showHideModalBottomSheetClickListener: () -> Unit,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed),
    )

    var sheetPeekHeight by remember { mutableStateOf(0.dp) }
    val sheetPeekHeightAnimated = animateDpAsState(targetValue = sheetPeekHeight)

    BottomSheetScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Demo UIkit"),
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        },
        sheetShape = AppTheme.shapes.large,
        sheetPeekHeight = sheetPeekHeightAnimated.value,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Bottom", style = AppTheme.typography.displayLarge)
                Text(text = "sheet", style = AppTheme.typography.displayLarge)
                Text(text = "example", style = AppTheme.typography.displayLarge)
            }
        },
        content = {
            BottomSheetScaffoldContent(
                navController = navController,
                paddingValues = it,
                showDialogClickListener = showDialogClickListener,
                showHideBottomSheetClickListener = {
                    sheetPeekHeight = if (sheetPeekHeight == 0.dp) 144.dp else 0.dp
                },
                showHideModalBottomSheetClickListener = showHideModalBottomSheetClickListener,
            )
        },
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun BottomSheetScaffoldContent(
    navController: NavHostController,
    paddingValues: PaddingValues,
    showDialogClickListener: () -> Unit,
    showHideBottomSheetClickListener: () -> Unit,
    showHideModalBottomSheetClickListener: () -> Unit,
) {
    val headerModifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
    val cardModifier = Modifier.padding(8.dp)

    val items = items(
        navController = navController,
        showDialogClickListener = showDialogClickListener,
        showHideBottomSheetClickListener = showHideBottomSheetClickListener,
        showHideModalBottomSheetClickListener = showHideModalBottomSheetClickListener,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 8.dp)
            .padding(paddingValues),
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

@Composable
private fun items(
    navController: NavHostController,
    showDialogClickListener: () -> Unit,
    showHideBottomSheetClickListener: () -> Unit,
    showHideModalBottomSheetClickListener: () -> Unit,
) = listOf(
    HeaderTileState.largePrimary(TextValue.Simple("Atoms")),
    BadgeTileState(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("1. Typography"),
        clickListener = { navController.navigate(DemoDestinations.Typography) }
    ),
    BadgeTileState(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("2. Palette"),
        clickListener = { navController.navigate(DemoDestinations.Palette) }
    ),
    BadgeTileState(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("3. Buttons"),
        clickListener = { navController.navigate(DemoDestinations.Buttons) }
    ),

    HeaderTileState.largePrimary(TextValue.Simple("Molecules")),
    BadgeTileState(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("1. Cell Tiles"),
        clickListener = { navController.navigate(DemoDestinations.CellTiles) }
    ),
    BadgeTileState(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("2. List Tiles"),
        clickListener = { navController.navigate(DemoDestinations.ListTiles) }
    ),
    BadgeTileState(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("3. Tag Lists"),
        clickListener = { navController.navigate(DemoDestinations.TagList) }
    ),
    BadgeTileState(
        header = TextValue.Simple("molecule"),
        value = TextValue.Simple("4. Information Tiles"),
        clickListener = { navController.navigate(DemoDestinations.InformationTiles) }
    ),

    HeaderTileState.largePrimary(TextValue.Simple("Organisms")),
    BadgeTileState(
        header = TextValue.Simple("organism"),
        value = TextValue.Simple("1. Message Tiles"),
        clickListener = { navController.navigate(DemoDestinations.Messages) }
    ),
    BadgeTileState(
        header = TextValue.Simple("organism"),
        value = TextValue.Simple("2. Num Pad"),
        clickListener = { navController.navigate(DemoDestinations.NumPad) }
    ),

    HeaderTileState.largePrimary(TextValue.Simple("Templates")),
    BadgeTileState(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("1. Alert Dialog"),
        clickListener = { showDialogClickListener() }
    ),
    BadgeTileState(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("2. Bottom sheet"),
        clickListener = { showHideBottomSheetClickListener() }
    ),
    BadgeTileState(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("3. Modal bottom sheet"),
        clickListener = { showHideModalBottomSheetClickListener() }
    ),
)