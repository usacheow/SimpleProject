package com.usacheow.appdemo

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.container.textValue
import com.usacheow.coredata.database.PreferencesProvider
import com.usacheow.coredata.database.ThemeMode
import com.usacheow.coredata.database.UserDataStorage
import com.usacheow.coreuicompose.tools.SystemBarsIconsColor
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.listtile.CellTileState
import com.usacheow.coreuicompose.uikit.other.Header
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.LocalWindowSizeClass
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val storage = remember { UserDataStorage(PreferencesProvider(context)) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()

    val mode by storage.themeModeFlow.collectAsState(initial = ThemeMode.System)
    val isDarkTheme = when (mode) {
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
        ThemeMode.System -> isSystemInDarkTheme()
    }
    AnimatedContent(targetState = isDarkTheme) { isDarkTheme ->
        AppTheme(
            windowSizeClass = LocalWindowSizeClass.current,
            isDarkTheme = isDarkTheme,
        ) {
            SystemBarsIconsColor(needWhiteAllIcons = isDarkTheme)
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    SimpleTopAppBar(
                        title = "Inputs".textValue(),
                        navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                        scrollBehavior = scrollBehavior,
                    )
                },
            ) { padding ->
                LazyColumn(
                    contentPadding = insetAllExcludeTop().asPaddingValues().add(padding).add(PaddingValues(24.dp)),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        Header(
                            value = "Settings".textValue(),
                            modifier = Modifier.padding(bottom = 24.dp),
                        )
                    }
                    designBlock(mode) {
                        scope.launch { storage.setThemeMode(it) }
                    }
                }
            }
        }
    }
}

private fun LazyListScope.designBlock(
    selectedMode: ThemeMode,
    onThemeModeClick: (ThemeMode) -> Unit,
) {
    @Composable
    fun ModeItem(
        icon: IconValue,
        value: TextValue,
        currentMode: ThemeMode,
        selectedMode: ThemeMode,
        onThemeModeClick: (ThemeMode) -> Unit,
    ) {
        CellTileState.Data(
            value = value,
            leftPart = CellTileState.LeftPart.GreyIcon(icon.toImageValue()),
            onClick = { onThemeModeClick(currentMode) },
            rightPart = if (selectedMode == currentMode) CellTileState.RightPart.CheckIcon else null
        ).Content(modifier = Modifier)
    }
    item {
        ModeItem(
            value = "System theme".textValue(),
            icon = AppTheme.specificIcons.themeSystem,
            currentMode = ThemeMode.System,
            selectedMode = selectedMode,
            onThemeModeClick = onThemeModeClick,
        )
        ModeItem(
            value = "Dark theme".textValue(),
            icon = AppTheme.specificIcons.themeDark,
            currentMode = ThemeMode.Dark,
            selectedMode = selectedMode,
            onThemeModeClick = onThemeModeClick,
        )
        ModeItem(
            value = "Light theme".textValue(),
            icon = AppTheme.specificIcons.themeLight,
            currentMode = ThemeMode.Light,
            selectedMode = selectedMode,
            onThemeModeClick = onThemeModeClick,
        )
    }
}