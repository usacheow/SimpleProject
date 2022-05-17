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
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.tools.insetTop
import com.usacheow.coreuicompose.uikit.ListTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTilesScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                scrollBehavior = scrollBehavior,
                title = TextValue.Simple("List tiles"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = insetTop(),
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
        rightImageInfo = AppTheme.specificIcons.navigateNext.toImageValue(),
        value = TextValue.Simple("Main information"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = AppTheme.specificIcons.account.toImageValue(),
        rightImageInfo = AppTheme.specificIcons.navigateNext.toImageValue(),
        value = TextValue.Simple("Main information"),
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = AppTheme.specificIcons.account.toImageValue(),
        rightImageInfo = AppTheme.specificIcons.navigateNext.toImageValue(),
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Simple("Top description"),
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
)