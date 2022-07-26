package com.usacheow.coreuicompose.uikit.duplicate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.compose.AppTheme
import kotlin.math.max
import kotlin.math.roundToInt

object SimpleTopAppBarConfig {

    val DefaultContentHeight = 64.dp

    @Composable
    fun surfaceColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surface,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurface,
        titleContentColor = AppTheme.specificColorScheme.onSurface,
        actionIconContentColor = AppTheme.specificColorScheme.onSurface,
    )

    @Composable
    fun surfaceVariantColors() = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.surfaceVariant,
        navigationIconContentColor = AppTheme.specificColorScheme.onSurfaceVariant,
        titleContentColor = AppTheme.specificColorScheme.onSurfaceVariant,
        actionIconContentColor = AppTheme.specificColorScheme.onSurfaceVariant,
    )
}

@Composable
fun SimpleTopAppBar(
    title: TextValue?,
    additional: @Composable () -> Unit = {},
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    contentPadding: PaddingValues = PaddingValues(),
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior,
) {
    SimpleTopAppBar(
        title = { title?.get()?.let { Text(it) } },
        additional = additional,
        titleTextStyle = titleTextStyle,
        navigationIcon = navigationIcon,
        actions = actions,
        contentPadding = contentPadding,
        colors = colors,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun SimpleTopAppBar(
    title: @Composable () -> Unit,
    additional: @Composable () -> Unit = {},
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    contentPadding: PaddingValues = PaddingValues(),
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val navigationIconUi = @Composable {
        if (navigationIcon != null) {
            TopAppBarNavIcon(
                iconValue = navigationIcon.first,
                onClick = { navigationIcon.second() },
            )
        }
    }
    val actionsUi = @Composable {
        Row {
            actions.toList().forEach {
                TopAppBarAction(
                    data = it,
                    defaultColor = colors.actionIconContentColor(scrollBehavior.scrollFraction).value,
                )
            }
        }
    }

    SimpleTopAppBarContainer(
        colors = colors,
        scrollBehavior = scrollBehavior,
    ) {
        val heightPx = LocalDensity.current.run { SimpleTopAppBarConfig.DefaultContentHeight.toPx() }
        Column {
            TopAppBarLayout(
                modifier = Modifier.padding(contentPadding),
                heightPx = heightPx,

                navigationIconContentColor = colors.navigationIconContentColor(scrollBehavior.scrollFraction).value,
                titleContentColor = colors.titleContentColor(scrollBehavior.scrollFraction).value,
                actionIconContentColor = colors.actionIconContentColor(scrollBehavior.scrollFraction).value,

                title = title,
                titleTextStyle = titleTextStyle,
                titleAlpha = 1f,
                titleVerticalArrangement = Arrangement.Center,
                titleHorizontalArrangement = Arrangement.Center,
                titleBottomPadding = 0,
                hideTitleSemantics = false,

                navigationIcon = navigationIconUi,
                actions = actionsUi,
            )
            additional()
        }
    }
}

@Composable
fun SimpleTopAppBar(
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable () -> Unit,
) {
    SimpleTopAppBarContainer(
        colors = colors,
        scrollBehavior = scrollBehavior,
    ) {
        content()
    }
}

@Composable
private fun SimpleTopAppBarContainer(
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable () -> Unit,
) {
    val offsetLimit = with(LocalDensity.current) { -SimpleTopAppBarConfig.DefaultContentHeight.toPx() / 4 }
    SideEffect {
        if (scrollBehavior.state.offsetLimit != offsetLimit) {
            scrollBehavior.state.offsetLimit = offsetLimit
        }
    }

    val scrollFraction = scrollBehavior.scrollFraction
    val appBarContainerColor by colors.containerColor(scrollFraction)

    Surface(color = appBarContainerColor) {
        content()
    }

//    Box(modifier = Modifier.background(colors.containerColor(scrollFraction = 0f).value)) {
//        content()
//        Box(modifier = Modifier
//            .align(Alignment.BottomCenter)
//            .height(1.dp)
//            .fillMaxWidth()
//            .background(appBarContainerColor))
//    }
}

@Composable
private fun TopAppBarLayout(
    modifier: Modifier,
    heightPx: Float,
    navigationIconContentColor: Color,
    titleContentColor: Color,
    actionIconContentColor: Color,
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle,
    titleAlpha: Float,
    titleVerticalArrangement: Arrangement.Vertical,
    titleHorizontalArrangement: Arrangement.Horizontal,
    titleBottomPadding: Int,
    hideTitleSemantics: Boolean,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
) {
    Layout(
        content = {
            Box(
                modifier = Modifier
                    .layoutId("navigationIcon")
                    .padding(start = 4.dp),
            ) {
                CompositionLocalProvider(LocalContentColor provides navigationIconContentColor, content = navigationIcon)
            }
            Box(
                modifier = Modifier
                    .layoutId("title")
                    .padding(horizontal = 4.dp)
                    .then(if (hideTitleSemantics) Modifier.clearAndSetSemantics { } else Modifier),
            ) {
                ProvideTextStyle(value = titleTextStyle) {
                    CompositionLocalProvider(LocalContentColor provides titleContentColor.copy(alpha = titleAlpha),
                        content = title)
                }
            }
            Box(
                modifier = Modifier
                    .layoutId("actionIcons")
                    .padding(end = 4.dp),
            ) {
                CompositionLocalProvider(LocalContentColor provides actionIconContentColor, content = actions)
            }
        },
        modifier = modifier,
    ) { measurables, constraints ->
        val navigationIconPlaceable = measurables.first { it.layoutId == "navigationIcon" }.measure(constraints)
        val actionIconsPlaceable = measurables.first { it.layoutId == "actionIcons" }.measure(constraints)

        val maxTitleWidth = constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width
        val titlePlaceable = measurables.first { it.layoutId == "title" }
            .measure(constraints.copy(maxWidth = maxTitleWidth)) // Locate the title's baseline.
        val titleBaseline = if (titlePlaceable[LastBaseline] != AlignmentLine.Unspecified) {
            titlePlaceable[LastBaseline]
        } else {
            0
        }

        val layoutHeight = heightPx.roundToInt()

        layout(constraints.maxWidth, layoutHeight) { // Navigation icon
            navigationIconPlaceable.placeRelative(x = 0, y = (layoutHeight - navigationIconPlaceable.height) / 2)

            // Title
            titlePlaceable.placeRelative(x = when (titleHorizontalArrangement) {
                Arrangement.Center -> (constraints.maxWidth - titlePlaceable.width) / 2
                Arrangement.End -> constraints.maxWidth - titlePlaceable.width - actionIconsPlaceable.width // Arrangement.Start.
                // An TopAppBarTitleInset will make sure the title is offset in case the
                // navigation icon is missing.
                else -> max(12.dp.roundToPx(), navigationIconPlaceable.width)
            }, y = when (titleVerticalArrangement) {
                Arrangement.Center -> (layoutHeight - titlePlaceable.height) / 2 // Apply bottom padding from the title's baseline only when the Arrangement is
                // "Bottom".
                Arrangement.Bottom -> if (titleBottomPadding == 0) layoutHeight - titlePlaceable.height
                else layoutHeight - titlePlaceable.height - max(0,
                    titleBottomPadding - titlePlaceable.height + titleBaseline) // Arrangement.Top
                else -> 0
            })

            // Action icons
            actionIconsPlaceable.placeRelative(x = constraints.maxWidth - actionIconsPlaceable.width,
                y = (layoutHeight - actionIconsPlaceable.height) / 2)
        }
    }
}