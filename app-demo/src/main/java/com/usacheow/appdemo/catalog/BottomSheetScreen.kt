package com.usacheow.appdemo.catalog

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.uikit.button.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryL
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipHiddenState = false,
        ),
    )

    BackHandler(enabled = bottomSheetScaffoldState.bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
    }

    BottomSheetScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            SimpleTopAppBar(
                title = "Bottom sheet".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                scrollBehavior = scrollBehavior,
            )
        },
        sheetPeekHeight = 120.dp,
        sheetContent = {
            SheetContent()
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Content(
                    showSheet = {
                        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.show() }
                    },
                    hideSheet = {
                        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    },
                )
            }
        },
    )
}

@Composable
private fun SheetContent(
    contentPadding: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = Modifier
            .padding(contentPadding)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Modal", style = AppTheme.specificTypography.displayLarge)
        Text(text = "bottom", style = AppTheme.specificTypography.displayLarge)
        Text(text = "sheet", style = AppTheme.specificTypography.displayLarge)
        Text(text = "example", style = AppTheme.specificTypography.displayLarge)
    }
}

@Composable
private fun Content(
    showSheet: () -> Unit,
    hideSheet: () -> Unit,
) {
    SimpleButtonPrimaryL(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = showSheet,
    ) {
        SimpleButtonContent(text = "Show sheet".textValue())
    }
    SimpleButtonPrimaryL(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = hideSheet,
    ) {
        SimpleButtonContent(text = "Hide sheet".textValue())
    }
}