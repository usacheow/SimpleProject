package com.usacheow.featureonboarding.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.featureonboarding.presentation.ScreenNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }

    OnBoardingScreen(
        onNextClick = router::toOnBoardingInfoScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OnBoardingScreen(
    onNextClick: () -> Unit,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("OnBoarding screen"),
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column {
            Text("OnBoarding text", modifier = Modifier.padding(24.dp))
            Button(onClick = onNextClick) {
                Text("Next screen")
            }
        }
    }
}