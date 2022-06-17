package com.usacheow.featuremain.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetBottomWithBottomNavigation
import com.usacheow.coreuicompose.uikit.CellTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.featuremain.presentation.ScreenNavigator
import com.usacheow.featuremain.presentation.viewmodel.AViewModel
import com.usacheow.featuremain.presentation.viewmodel.CViewModel

@Composable
fun MainAScreen(
    navHostController: NavHostController,
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val viewModel = hiltViewModel<AViewModel>()
    val cviewModel = hiltViewModel<CViewModel>()

    MainAScreen(
        onNextClick = router::toMock1SecondScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainAScreen(
    onNextClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Main A screen"),
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = insetBottomWithBottomNavigation(),
        ) {
            items(20) {
                CellTileState.Data(
                    title = TextValue.Simple("item $it"),
                    clickListener = onNextClick,
                ).Content(modifier = Modifier)
            }
        }
    }
}