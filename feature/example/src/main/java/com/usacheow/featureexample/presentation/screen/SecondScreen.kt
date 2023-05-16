package com.usacheow.featureexample.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getScreenModel
import com.usacheow.corecommon.container.textValue
import com.usacheow.corenavigation.base.rememberNavigator
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.featureexample.presentation.ScreenNavigator
import com.usacheow.featureexample.presentation.viewmodel.SecondViewModel

data class SecondScreen(val index: String) : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = rememberNavigator(::ScreenNavigator)
        val viewModel = getScreenModel<SecondViewModel, SecondViewModel.Factory> { factory ->
            factory.create(index)
        }

        SecondScreen(
            index = viewModel.index,
            onBackClick = navigator::back,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SecondScreen(
    index: String,
    onBackClick: () -> Boolean,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Second screen".textValue(),
                navigationIcon = AppTheme.specificIcons.back to onBackClick,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text("Argument = $index", modifier = Modifier.padding(24.dp))
        }
    }
}