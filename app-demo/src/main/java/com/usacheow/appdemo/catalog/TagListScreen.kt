package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Column
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
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.TagTileState
import com.usacheow.coreuicompose.uikit.barcopy.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagListScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    var selectedRadioButton by remember { mutableStateOf<Int?>(null) }
    var selectedCheckboxButtons by remember { mutableStateOf<List<Int>>(emptyList()) }

    val radioButtons = radioButtons(selectedRadioButton) {
        selectedRadioButton = it
    }
    val checkButtons = checkButtons(selectedCheckboxButtons) {
        selectedCheckboxButtons = if (selectedCheckboxButtons.contains(it)) {
            selectedCheckboxButtons.toMutableList().apply { remove(it) }
        } else {
            selectedCheckboxButtons + it
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Tag tiles"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp),
                contentPadding = getBottomInset(),
            ) {
                item(span = { GridItemSpan(2) }) {
                    HeaderTileState(
                        value = TextValue.Simple("Single selection mode"),
                        type = HeaderTileState.Type.MediumPrimary,
                    ).Content(Modifier.padding(8.dp))
                }
                items(radioButtons) {
                    it.Content(Modifier.padding(8.dp))
                }
                item(span = { GridItemSpan(2) }) {
                    HeaderTileState(
                        value = TextValue.Simple("Multi selection mode"),
                        type = HeaderTileState.Type.MediumPrimary,
                    ).Content(Modifier
                        .padding(top = 8.dp)
                        .padding(8.dp))
                }
                items(checkButtons) {
                    it.Content(Modifier.padding(8.dp))
                }
            }
        }
    }
}

private fun radioButtons(selected: Int?, clickListener: (Int) -> Unit) = List(8) {
    TagTileState(
        text = TextValue.Simple("Radio tag $it"),
        clickListener = { clickListener(it) },
        isSelected = selected == it,
    )
}

private fun checkButtons(selected: List<Int>, clickListener: (Int) -> Unit) = List(8) {
    TagTileState(
        text = TextValue.Simple("Check tag $it"),
        clickListener = { clickListener(it) },
        isSelected = selected.contains(it),
    )
}