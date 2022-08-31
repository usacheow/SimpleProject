package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.HeaderTileState
import com.usacheow.coreuicompose.uikit.listtile.TagTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagListScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

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
                title = "Tag tiles".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxHeight(),
                contentPadding = insetAllExcludeTop().asPaddingValues().add(it).add(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item(span = { GridItemSpan(2) }) {
                    HeaderTileState.Data(
                        value = "Single selection mode".textValue(),
                        type = HeaderTileState.Type.Medium,
                    ).Content(Modifier)
                }
                items(radioButtons) {
                    it.Content(Modifier)
                }
                item(span = { GridItemSpan(2) }) {
                    HeaderTileState.Data(
                        value = "Multi selection mode".textValue(),
                        type = HeaderTileState.Type.Medium,
                    ).Content(Modifier.padding(top = 8.dp))
                }
                items(checkButtons) {
                    it.Content(Modifier)
                }
            }
        }
    }
}

private fun radioButtons(selected: Int?, onClick: (Int) -> Unit) = List(8) {
    TagTileState.Data(
        text = "Radio tag $it".textValue(),
        onClick = { onClick(it) },
        isSelected = selected == it,
    )
}

private fun checkButtons(selected: List<Int>, onClick: (Int) -> Unit) = List(8) {
    TagTileState.Data(
        text = "Check tag $it".textValue(),
        onClick = { onClick(it) },
        isSelected = selected.contains(it),
    )
}