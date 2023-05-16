package com.usacheow.showcaseapp.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.usacheow.showcaseapp.DemoScreens
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.LocalBottomNavigationHeight
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.listtile.BadgeTileState
import com.usacheow.coreuicompose.uikit.status.SimpleAlertDialogUi
import com.usacheow.coreuitheme.compose.LocalWindowSizeClass

class DemoScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val windowSizeClass = LocalWindowSizeClass.current
        var isDialogVisible by remember { mutableStateOf(false) }
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        val typographyScreen = rememberScreen(DemoScreens.Typography)
        val paletteScreen = rememberScreen(DemoScreens.Palette)
        val settingsScreen = rememberScreen(DemoScreens.Settings)
        val buttonsScreen = rememberScreen(DemoScreens.Buttons)
        val inputsScreen = rememberScreen(DemoScreens.Inputs)
        val cellTilesScreen = rememberScreen(DemoScreens.CellTiles)
        val tagListScreen = rememberScreen(DemoScreens.TagList)
        val informationTilesScreen = rememberScreen(DemoScreens.InformationTiles)
        val messagesScreen = rememberScreen(DemoScreens.Messages)
        val numPadScreen = rememberScreen(DemoScreens.NumPad)
        val bottomSheetScreen = rememberScreen(DemoScreens.BottomSheet)
        val modalBottomSheetScreen = rememberScreen(DemoScreens.ModalBottomSheet)
        val bottomBarScreen = rememberScreen(DemoScreens.BottomBar)

        val modifier = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> Modifier.fillMaxWidth()
            else -> Modifier.width(700.dp)
        }

        val columnsCount = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 2
            else -> 4
        }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = "UiKit".textValue(),
                    scrollBehavior = scrollBehavior,
                )
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnsCount),
                        contentPadding = insetAllExcludeTop().asPaddingValues().add(it)
                            .add(horizontal = 24.dp, vertical = 8.dp),
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(
                            navigator = navigator,
                            showDialogClickListener = { isDialogVisible = true },
                            maxSpanCount = columnsCount,
                            typographyScreen = typographyScreen,
                            paletteScreen = paletteScreen,
                            settingsScreen = settingsScreen,
                            buttonsScreen = buttonsScreen,
                            inputsScreen = inputsScreen,
                            cellTilesScreen = cellTilesScreen,
                            tagListScreen = tagListScreen,
                            informationTilesScreen = informationTilesScreen,
                            messagesScreen = messagesScreen,
                            numPadScreen = numPadScreen,
                            bottomSheetScreen = bottomSheetScreen,
                            modalBottomSheetScreen = modalBottomSheetScreen,
                            bottomBarScreen = bottomBarScreen,
                        )
                    }
                }
            },
        )

        if (isDialogVisible) {
            SimpleAlertDialogUi(
                text = "Material dialog example".textValue(),
                buttonText = "Ok".textValue(),
                onClickRequest = { isDialogVisible = false },
            )
        }
    }

    private fun LazyGridScope.items(
        navigator: Navigator,
        showDialogClickListener: () -> Unit,
        maxSpanCount: Int,
        typographyScreen: Screen,
        paletteScreen: Screen,
        settingsScreen: Screen,
        buttonsScreen: Screen,
        inputsScreen: Screen,
        cellTilesScreen: Screen,
        tagListScreen: Screen,
        informationTilesScreen: Screen,
        messagesScreen: Screen,
        numPadScreen: Screen,
        bottomSheetScreen: Screen,
        modalBottomSheetScreen: Screen,
        bottomBarScreen: Screen,
    ) {
        item {
            BadgeTileState.Data(
                header = "theme".textValue(),
                value = "1. Typography".textValue(),
                onClick = { navigator.push(typographyScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "theme".textValue(),
                value = "2. Palette".textValue(),
                onClick = { navigator.push(paletteScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "theme".textValue(),
                value = "3. Settings".textValue(),
                onClick = { navigator.push(settingsScreen) }
            ).Content(modifier = Modifier)
        }

        item(span = { GridItemSpan(maxSpanCount) }) { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            BadgeTileState.Data(
                header = "components".textValue(),
                value = "1. Buttons".textValue(),
                onClick = { navigator.push(buttonsScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "components".textValue(),
                value = "2. Inputs".textValue(),
                onClick = { navigator.push(inputsScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "tiles".textValue(),
                value = "3. Cell Tiles".textValue(),
                onClick = { navigator.push(cellTilesScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "tiles".textValue(),
                value = "4. Tag Lists".textValue(),
                onClick = { navigator.push(tagListScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "tiles".textValue(),
                value = "5. Information Tiles".textValue(),
                onClick = { navigator.push(informationTilesScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "tiles".textValue(),
                value = "6. Message Tiles".textValue(),
                onClick = { navigator.push(messagesScreen) }
            ).Content(modifier = Modifier)
        }

        item(span = { GridItemSpan(maxSpanCount) }) { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            BadgeTileState.Data(
                header = "ui".textValue(),
                value = "1. Num Pad".textValue(),
                onClick = { navigator.push(numPadScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "ui".textValue(),
                value = "2. Alert Dialog".textValue(),
                onClick = { showDialogClickListener() }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "ui".textValue(),
                value = "3. Bottom sheet".textValue(),
                onClick = { navigator.push(bottomSheetScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "ui".textValue(),
                value = "4. Modal bottom sheet".textValue(),
                onClick = { navigator.push(modalBottomSheetScreen) }
            ).Content(modifier = Modifier)
        }
        item {
            BadgeTileState.Data(
                header = "ui".textValue(),
                value = "5. Bottom bar".textValue(),
                onClick = { navigator.push(bottomBarScreen) }
            ).Content(modifier = Modifier)
        }
    }
}