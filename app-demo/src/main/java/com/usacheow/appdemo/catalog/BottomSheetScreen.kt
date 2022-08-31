package com.usacheow.appdemo.catalog

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.uikit.button.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryL
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.simpleSheetParams
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed),
    )
    val sheetParams = simpleSheetParams(bottomSheetScaffoldState.bottomSheetState)

    var sheetPeekHeight by remember { mutableStateOf(0.dp) }
    val sheetPeekHeightAnimated = animateDpAsState(targetValue = sheetPeekHeight)

    BackHandler(enabled = sheetPeekHeight != 0.dp) {
        sheetPeekHeight = 0.dp
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
        sheetShape = sheetParams.sheetShape,
        sheetPeekHeight = sheetPeekHeightAnimated.value,
        sheetContent = {
            SheetContent(sheetParams.sheetContentPadding)
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Content(
                    showSheet = { sheetPeekHeight = 144.dp },
                    hideSheet = { sheetPeekHeight = 0.dp },
                )
            }
        },
    )
}

@Composable
private fun SheetContent(
    contentPadding: PaddingValues,
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