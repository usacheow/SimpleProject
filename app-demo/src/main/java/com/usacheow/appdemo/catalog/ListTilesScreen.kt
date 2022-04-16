package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.ListTileState
import com.usacheow.coreuicompose.uikit.barcopy.SimpleTopAppBar
import com.usacheow.coreuitheme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTilesScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                scrollBehavior = scrollBehavior,
                title = TextValue.Simple("List tiles"),
                navigationIcon = R.drawable.ic_back to navController::popBackStack,
                contentPadding = getTopInset(),
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = getBottomInset(),
        ) {
            items(items()) {
                it.Content(Modifier)
            }
        }
    }
}

private fun items(): List<WidgetState> = listOf(
    ListTileState.shimmer(),
    ListTileState(
        value = TextValue.Simple("Main information"),
    ),
    ListTileState(
        value = TextValue.Simple("Main information"),
        bottomDescription = TextValue.Simple("Bottom description"),
    ),
    ListTileState(
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Simple("Top description"),
        bottomDescription = TextValue.Simple("Bottom description"),
    ),
    ListTileState(
        rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
        value = TextValue.Simple("Main information"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Vector(Icons.Default.AccountCircle),
        rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
        value = TextValue.Simple("Main information"),
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Vector(Icons.Default.AccountCircle),
        rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Simple("Top description"),
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
)