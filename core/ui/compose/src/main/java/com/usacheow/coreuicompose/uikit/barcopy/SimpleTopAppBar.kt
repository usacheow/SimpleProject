package com.usacheow.coreuicompose.uikit.barcopy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.compose.AppTheme
import kotlin.math.max
import kotlin.math.roundToInt

private typealias OnIconClick = () -> Unit
private typealias OnBackIconClick = () -> Boolean

@Composable
fun SimpleTopAppBar(
    title: TextValue?,
    titleTextStyle: TextStyle = AppTheme.typography.titleLarge,
    navigationIcon: Pair<ImageVector, OnBackIconClick>? = null,
    actions: Map<Int, OnIconClick> = emptyMap(),
    contentPadding: PaddingValues = PaddingValues(),
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val offsetLimit = with(LocalDensity.current) { -SimpleTopAppBarConfig.ContainerHeight.toPx() / 4 }
    SideEffect {
        if (scrollBehavior.offsetLimit != offsetLimit) {
            scrollBehavior.offsetLimit = offsetLimit
        }
    }

    val scrollFraction = scrollBehavior.scrollFraction
    val appBarContainerColor by colors.containerColor(scrollFraction)

    val navigationIconUi = @Composable {
        if (navigationIcon != null) {
            Icon(
                imageVector = navigationIcon.first,
                tint = AppTheme.specificColorScheme.symbolPrimary,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = { navigationIcon.second() })
                    .padding(8.dp),
            )
        }
    }
    val actionsUi = @Composable {
        if (actions.toList().isNotEmpty()) {
            Row {
                actions.toList().forEach {
                    Icon(
                        painter = painterResource(it.first),
                        contentDescription = null,
                        tint = colors.actionIconContentColor(scrollBehavior.scrollFraction).value,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(enabled = true, onClick = it.second)
                            .padding(12.dp),
                    )
                }
            }
        }
    }

    Surface(color = appBarContainerColor) {
        val height = SimpleTopAppBarConfig.ContainerHeight +
                contentPadding.calculateTopPadding() +
                contentPadding.calculateBottomPadding()
        val heightPx = LocalDensity.current.run { height.toPx() }
        TopAppBarLayout(
            modifier = Modifier.padding(contentPadding),
            heightPx = heightPx,

            navigationIconContentColor = colors.navigationIconContentColor(scrollBehavior.scrollFraction).value,
            titleContentColor = colors.titleContentColor(scrollBehavior.scrollFraction).value,
            actionIconContentColor = colors.actionIconContentColor(scrollBehavior.scrollFraction).value,

            title = { title?.get()?.let { Text(it) } },
            titleTextStyle = titleTextStyle,
            titleAlpha = 1f,
            titleVerticalArrangement = Arrangement.Center,
            titleHorizontalArrangement = Arrangement.Center,
            titleBottomPadding = 0,
            hideTitleSemantics = false,

            navigationIcon = navigationIconUi,
            actions = actionsUi,
        )
    }
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
        {
            Box(Modifier
                .layoutId("navigationIcon")
                .padding(start = 4.dp)) {
                CompositionLocalProvider(
                    LocalContentColor provides navigationIconContentColor,
                    content = navigationIcon
                )
            }
            Box(
                Modifier
                    .layoutId("title")
                    .padding(horizontal = 4.dp)
                    .then(if (hideTitleSemantics) Modifier.clearAndSetSemantics { } else Modifier)
            ) {
                ProvideTextStyle(value = titleTextStyle) {
                    CompositionLocalProvider(
                        LocalContentColor provides titleContentColor.copy(alpha = titleAlpha),
                        content = title
                    )
                }
            }
            Box(Modifier
                .layoutId("actionIcons")
                .padding(end = 4.dp)) {
                CompositionLocalProvider(
                    LocalContentColor provides actionIconContentColor,
                    content = actions
                )
            }
        },
        modifier = modifier
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables.first { it.layoutId == "navigationIcon" }.measure(constraints)
        val actionIconsPlaceable =
            measurables.first { it.layoutId == "actionIcons" }.measure(constraints)

        val maxTitleWidth =
            constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width
        val titlePlaceable =
            measurables
                .first { it.layoutId == "title" }
                .measure(constraints.copy(maxWidth = maxTitleWidth))
        // Locate the title's baseline.
        val titleBaseline =
            if (titlePlaceable[LastBaseline] != AlignmentLine.Unspecified) {
                titlePlaceable[LastBaseline]
            } else {
                0
            }

        val layoutHeight = heightPx.roundToInt()

        layout(constraints.maxWidth, layoutHeight) {
            // Navigation icon
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = (layoutHeight - navigationIconPlaceable.height) / 2
            )

            // Title
            titlePlaceable.placeRelative(
                x = when (titleHorizontalArrangement) {
                    Arrangement.Center -> (constraints.maxWidth - titlePlaceable.width) / 2
                    Arrangement.End ->
                        constraints.maxWidth - titlePlaceable.width - actionIconsPlaceable.width
                    // Arrangement.Start.
                    // An TopAppBarTitleInset will make sure the title is offset in case the
                    // navigation icon is missing.
                    else -> max(12.dp.roundToPx(), navigationIconPlaceable.width)
                },
                y = when (titleVerticalArrangement) {
                    Arrangement.Center -> (layoutHeight - titlePlaceable.height) / 2
                    // Apply bottom padding from the title's baseline only when the Arrangement is
                    // "Bottom".
                    Arrangement.Bottom ->
                        if (titleBottomPadding == 0) layoutHeight - titlePlaceable.height
                        else layoutHeight - titlePlaceable.height - max(
                            0,
                            titleBottomPadding - titlePlaceable.height + titleBaseline
                        )
                    // Arrangement.Top
                    else -> 0
                }
            )

            // Action icons
            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = (layoutHeight - actionIconsPlaceable.height) / 2
            )
        }
    }
}

private object SimpleTopAppBarConfig {
    val ContainerHeight = 64.dp
}