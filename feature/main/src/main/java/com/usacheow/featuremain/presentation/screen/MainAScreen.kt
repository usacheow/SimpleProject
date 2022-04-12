package com.usacheow.featuremain.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getBottomInsetWithBottomNavigation
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.ListTile
import com.usacheow.coreuicompose.uikit.ListTileState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.featuremain.presentation.BScreenArg
import com.usacheow.featuremain.presentation.ScreenNavigator
import com.usacheow.featuremain.presentation.viewmodels.AViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAScreen(
    graphRoute: String,
    navHostController: NavHostController,
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val parentEntry = remember {
        navHostController.getBackStackEntry(graphRoute)
    }
    val aViewModel = hiltViewModel<AViewModel>(parentEntry)

    MainAScreen(
        selectionNumber = aViewModel.x,
        onNextClick = {
            aViewModel.x += 1
            router.toBScreen(BScreenArg(it))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainAScreen(
    selectionNumber: Int,
    onNextClick: (String) -> Unit,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Main A screen $selectionNumber"),
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn(
            contentPadding = getBottomInsetWithBottomNavigation(),
        ) {
            items(20) {
                ListTileState(
                    value = TextValue.Simple("item $it"),
                    clickListener = { onNextClick(it.toString()) },
                ).Content(modifier = Modifier)
            }
        }
    }
}