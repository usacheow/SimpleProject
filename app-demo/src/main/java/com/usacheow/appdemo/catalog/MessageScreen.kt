package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.listtile.MessageBannerState
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Message tiles".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = insetAllExcludeTop().asPaddingValues().add(it).add(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(items) {
                it.Content(Modifier)
            }
        }
    }
}

@Composable
private fun items(): List<TileState> = listOf(
    MessageBannerState(
        title = "Message title text".textValue(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ),
    MessageBannerState(
        title = "Message title text".textValue(),
        description = "Message description text".textValue(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ),
    MessageBannerState(
        icon = AppTheme.specificIcons.error.toImageValue(),
        title = "Message title text".textValue(),
        description = "Message description text".textValue(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ),
    MessageBannerState(
        title = "Message title text".textValue(),
        button = "Button".textValue(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {},
    ),
    MessageBannerState(
        title = "Message title text".textValue(),
        description = "Message description text".textValue(),
        button = "Button".textValue(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {},
    ),
    MessageBannerState(
        icon = AppTheme.specificIcons.error.toImageValue(),
        title = "Message title text".textValue(),
        description = "Message description text".textValue(),
        button = "Button".textValue(),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {},
    ),
)