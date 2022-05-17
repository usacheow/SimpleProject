package com.usacheow.featureonboarding.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.featureonboarding.presentation.ScreenNavigator

@Composable
fun OnBoardingInfoScreen(
    navHostController: NavHostController
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }

    OnBoardingInfoScreen(
        onBackClick = router::back,
        onNextClick = router::toMainFeature,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OnBoardingInfoScreen(
    onBackClick: () -> Boolean,
    onNextClick: () -> Unit,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("OnBoarding info screen"),
                navigationIcon = AppTheme.specificIcons.back to onBackClick,
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text("OnBoarding info text", modifier = Modifier.padding(24.dp))
            Button(onClick = onNextClick) {
                Text("Next feature")
            }
        }
    }
}