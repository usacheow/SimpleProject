package com.usacheow.featureexample.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
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
        onNextClick = router::toOnBoardingInfoScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstScreen(
    onNextClick: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "First screen".textValue(),
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text("First screen content", modifier = Modifier.padding(24.dp))
            Button(onClick = { onNextClick("Arg") }) {
                Text("Second screen")
            }
        }
    }
}