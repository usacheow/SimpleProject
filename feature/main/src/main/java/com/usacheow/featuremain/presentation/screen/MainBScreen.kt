package com.usacheow.featuremain.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.barcopy.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import com.usacheow.featuremain.presentation.ScreenNavigator
import com.usacheow.featuremain.presentation.viewmodels.AViewModel
import com.usacheow.featuremain.presentation.viewmodels.BViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBScreen(
    graphRoute: String,
    navBackStackEntry: NavBackStackEntry,
    navHostController: NavHostController,
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val parentEntry = remember {
        navHostController.getBackStackEntry(graphRoute)
    }
    val aViewModel: AViewModel = hiltViewModel(parentEntry)
    val bViewModel: BViewModel = hiltViewModel(navBackStackEntry)

    LaunchedEffect(key1 = Unit) {
        bViewModel.x += 1
    }

    MainBScreen(
        selectionNumber = aViewModel.x,
        index = bViewModel.index ?: "no arg",
        onBackClick = router::back,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainBScreen(
    selectionNumber: Int,
    index: String,
    onBackClick: () -> Boolean,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Main B screen $selectionNumber $index"),
                navigationIcon = R.drawable.ic_back to onBackClick,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Text("Main B text", modifier = Modifier.padding(24.dp))
    }
}