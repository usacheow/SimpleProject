package com.usacheow.featureexample.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.featureexample.presentation.ScreenNavigator
import com.usacheow.featuremain.presentation.viewmodel.SecondViewModel

@Composable
fun SecondScreen(
    navHostController: NavHostController
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }
    val viewModel: SecondViewModel = hiltViewModel()

    SecondScreen(
        onBackClick = router::back,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SecondScreen(
    onBackClick: () -> Boolean,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Second screen"),
                navigationIcon = AppTheme.specificIcons.back to onBackClick,
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text("Second screen content", modifier = Modifier.padding(24.dp))
        }
    }
}