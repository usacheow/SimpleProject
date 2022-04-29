package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.appdemo.DemoDestinations
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.BadgeTileState
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(navController: NavHostController) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    val headerModifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
    val cardModifier = Modifier.padding(8.dp)

    val items = items(
        navController = navController,
        showDialogClickListener = { isDialogVisible = true },
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Demo UIkit"),
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        },
        content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .padding(it),
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
    BadgeTileState(
        header = TextValue.Simple("atom"),
        value = TextValue.Simple("4. Inputs"),
        clickListener = { navController.navigate(DemoDestinations.Inputs) }
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
        clickListener = { navController.navigate(DemoDestinations.BottomSheet) }
    ),
    BadgeTileState(
        header = TextValue.Simple("template"),
        value = TextValue.Simple("3. Modal bottom sheet"),
        clickListener = { navController.navigate(DemoDestinations.ModalBottomSheet) }
    ),
)