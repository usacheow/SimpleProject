package com.usacheow.showcaseapp.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.uikit.button.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryL
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import kotlinx.coroutines.launch

class ModalBottomSheetScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = "Modal bottom sheet".textValue(),
                    navigationIcon = AppTheme.specificIcons.back to navigator::pop,
                    scrollBehavior = scrollBehavior,
                )
            },
        ) {
            SimpleButtonPrimaryL(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                },
            ) {
                SimpleButtonContent(text = "Open sheet".textValue())
            }

            if (modalBottomSheetState.isVisible) ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                },
                sheetState = modalBottomSheetState,
            ) {
                SheetContent()
            }
        }
    }

    @Composable
    private fun SheetContent(
        contentPadding: PaddingValues = PaddingValues(),
    ) {
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Modal", style = AppTheme.specificTypography.displayLarge)
            Text(text = "bottom", style = AppTheme.specificTypography.displayLarge)
            Text(text = "sheet", style = AppTheme.specificTypography.displayLarge)
            Text(text = "example", style = AppTheme.specificTypography.displayLarge)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Content(
        navController: NavHostController,
        showSheet: () -> Unit,
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = "Modal bottom sheet".textValue(),
                    navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                    scrollBehavior = scrollBehavior,
                )
            },
        ) {
            SimpleButtonPrimaryL(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxWidth(),
                onClick = showSheet,
            ) {
                SimpleButtonContent(text = "Open sheet".textValue())
            }
        }
    }
}