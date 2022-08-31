package com.usacheow.featureexample.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.uikit.button.SimpleButtonTonalL
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.featureexample.presentation.ScreenNavigator
import com.usacheow.featureexample.presentation.viewmodel.FirstViewModel

@Composable
fun FirstScreen(
    navHostController: NavHostController,
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val viewModel: FirstViewModel = hiltViewModel()

    FirstScreen(
        onNextClick = router::toSecondScreen,
        onBottomBarClick = router::toBottomBarScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstScreen(
    onNextClick: (String) -> Unit,
    onBottomBarClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "First example screen".textValue(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 64.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            SimpleButtonTonalL(modifier = Modifier.fillMaxWidth(), onClick = { onNextClick("42") }) {
                Text("Second screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            SimpleButtonTonalL(modifier = Modifier.fillMaxWidth(), onClick = { onBottomBarClick() }) {
                Text("Bottom bar screen")
            }
        }
    }
}