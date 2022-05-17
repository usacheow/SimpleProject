package com.usacheow.featuremain.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import com.usacheow.coreuicompose.tools.insetTop
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.featuremain.presentation.ScreenNavigator

@Composable
fun Mock2Screen(
    navHostController: NavHostController
) {
    val router = remember(navHostController) { ScreenNavigator(navHostController) }

    Mock2Screen(
        onNextClick = router::toMock2BScreen,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Mock2Screen(
    onNextClick: () -> Unit,
) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Mock 2 screen"),
                contentPadding = insetTop(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column {
            Text("Mock 2 text", modifier = Modifier.padding(24.dp))
            Button(onClick = onNextClick) {
                Text("Next screen")
            }
        }
    }
}