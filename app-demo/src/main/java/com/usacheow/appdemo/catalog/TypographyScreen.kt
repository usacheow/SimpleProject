package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.add
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.listtile.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypographyScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Typography".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = insetAllExcludeTop().asPaddingValues().add(it).add(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(items = items) {
                it.Content(modifier = Modifier)
            }
        }
    }
}

@Composable
private fun items() = listOf(
    HeaderTileState.Data(
        value = "Name".textValue(),
        action = HeaderTileState.Action.Text("action".textValue(), {}),
        type = HeaderTileState.Type.Small,
    ),
    TextItem(AppTheme.specificTypography.displayLarge, "Display L"),
    TextItem(AppTheme.specificTypography.displayMedium, "Display M"),
    TextItem(AppTheme.specificTypography.displaySmall, "Display S"),

    TextItem(AppTheme.specificTypography.headlineLarge, "Headline L"),
    TextItem(AppTheme.specificTypography.headlineMedium, "Headline M"),
    TextItem(AppTheme.specificTypography.headlineSmall, "Headline S"),

    TextItem(AppTheme.specificTypography.titleLarge, "Title L"),
    TextItem(AppTheme.specificTypography.titleMedium, "Title M"),
    TextItem(AppTheme.specificTypography.titleSmall, "Title S"),

    TextItem(AppTheme.specificTypography.bodyLarge, "Body L"),
    TextItem(AppTheme.specificTypography.bodyMedium, "Body M"),
    TextItem(AppTheme.specificTypography.bodySmall, "Body S"),

    TextItem(AppTheme.specificTypography.labelLarge, "Label L"),
    TextItem(AppTheme.specificTypography.labelMedium, "Label M"),
    TextItem(AppTheme.specificTypography.labelSmall, "Label S"),
)

data class TextItem(
    val style: TextStyle,
    val name: String,
) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        Row(modifier = modifier.fillMaxWidth()) {
            Text(
                text = name,
                style = style,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
            )
            Text(
                text = style.fontSize.value.toInt().toString(),
                style = style,
            )
        }
    }
}