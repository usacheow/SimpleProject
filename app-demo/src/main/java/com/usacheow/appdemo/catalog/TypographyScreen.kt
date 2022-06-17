package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetAllExcludeTop
import com.usacheow.coreuicompose.uikit.HeaderTileState
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypographyScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())
    val items = items()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Typography"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            contentPadding = insetAllExcludeTop(),
        ) {
            items(items = items) {
                it.Content(Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
private fun items() = listOf(
    HeaderTileState.Data(
        value = TextValue.Simple("Name"),
        action = HeaderTileState.Action.Text(TextValue.Simple("action")),
        type = HeaderTileState.Type.SmallSecondary,
    ),
    TextItem(AppTheme.typography.displayLarge, "Display L"),
    TextItem(AppTheme.typography.displayMedium, "Display M"),
    TextItem(AppTheme.typography.displaySmall, "Display S"),

    TextItem(AppTheme.typography.headlineLarge, "Headline L"),
    TextItem(AppTheme.typography.headlineMedium, "Headline M"),
    TextItem(AppTheme.typography.headlineSmall, "Headline S"),

    TextItem(AppTheme.typography.titleLarge, "Title L"),
    TextItem(AppTheme.typography.titleMedium, "Title M"),
    TextItem(AppTheme.typography.titleSmall, "Title S"),

    TextItem(AppTheme.typography.bodyLarge, "Body L"),
    TextItem(AppTheme.typography.bodyMedium, "Body M"),
    TextItem(AppTheme.typography.bodySmall, "Body S"),

    TextItem(AppTheme.typography.labelLarge, "Label L"),
    TextItem(AppTheme.typography.labelMedium, "Label M"),
    TextItem(AppTheme.typography.labelSmall, "Label S"),
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