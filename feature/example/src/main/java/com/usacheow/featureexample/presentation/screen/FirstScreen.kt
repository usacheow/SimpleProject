package com.usacheow.featureexample.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import com.usacheow.corecommon.container.textValue
import com.usacheow.corenavigation.base.rememberNavigator
import com.usacheow.coreuicompose.uikit.button.SimpleButton
import com.usacheow.coreuicompose.uikit.button.SimpleButtonColors
import com.usacheow.coreuicompose.uikit.button.SimpleButtonSize
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.featureexample.presentation.ScreenNavigator
import com.usacheow.featureexample.presentation.viewmodel.FirstViewModel

class FirstScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = rememberNavigator(::ScreenNavigator)
        val viewModel = rememberScreenModel<FirstViewModel>()

        FirstScreen(
            onNextClick = navigator::toSecondScreen,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstScreen(
    onNextClick: (String) -> Unit,
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
            SimpleButton(
                modifier = Modifier.fillMaxWidth(),
                size = SimpleButtonSize.l(),
                colors = SimpleButtonColors.tonal(),
                onClick = { onNextClick("42") }) {
                Text("Second screen")
            }
        }
    }
}