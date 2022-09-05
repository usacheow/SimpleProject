package com.usacheow.coreuicompose.uikit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.usacheow.coreuitheme.compose.AppTheme

@Composable
fun SimpleButtonPrimaryS(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.s(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.primary(),
        content = content,
    )
}

@Composable
fun SimpleButtonPrimaryM(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.m(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.primary(),
        content = content,
    )
}

@Composable
fun SimpleButtonPrimaryL(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.l(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.primary(),
        content = content,
    )
}

@Composable
fun SimpleButtonSecondaryS(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.s(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.secondary(),
        content = content,
    )
}

@Composable
fun SimpleButtonSecondaryM(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.m(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.secondary(),
        content = content,
    )
}

@Composable
fun SimpleButtonSecondaryL(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.l(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.secondary(),
        content = content,
    )
}

@Composable
fun SimpleButtonTonalS(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.s(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.tonal(),
        content = content,
    )
}

@Composable
fun SimpleButtonTonalM(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.m(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.tonal(),
        content = content,
    )
}

@Composable
fun SimpleButtonTonalL(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.l(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.tonal(),
        content = content,
    )
}

@Composable
fun SimpleButtonOutlineS(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.s(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.outline(),
        border = if (enabled) SimpleButtonConfig.borderOutline() else null,
        content = content,
    )
}

@Composable
fun SimpleButtonOutlineM(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.m(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.outline(),
        border = if (enabled) SimpleButtonConfig.borderOutline() else null,
        content = content,
    )
}

@Composable
fun SimpleButtonOutlineL(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.l(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.outline(),
        border = if (enabled) SimpleButtonConfig.borderOutline() else null,
        content = content,
    )
}

@Composable
fun SimpleButtonInlineS(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.s(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.inline(),
        content = content,
    )
}

@Composable
fun SimpleButtonInlineM(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.m(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.inline(),
        content = content,
    )
}

@Composable
fun SimpleButtonInlineL(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    SimpleButton(
        modifier = modifier,
        size = SimpleButtonSize.l(),
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = SimpleButtonColors.inline(),
        content = content,
    )
}

object SimpleButtonConfig {

    @Composable
    fun borderOutline() = BorderStroke(width = 1.dp, color = AppTheme.specificColorScheme.outline)
}

data class SimpleButtonSize(
    val heightModifier: Modifier,
    val contentPadding: PaddingValues,
    val shape: Shape,
    val textStyle: TextStyle,
) {

    companion object {

        @Composable
        fun s() = SimpleButtonSize(
            heightModifier = Modifier.sizeIn(minWidth = 32.dp, minHeight = 32.dp),
            contentPadding = PaddingValues(10.dp),
            shape = CircleShape,
            textStyle = AppTheme.specificTypography.labelMedium,
        )

        @Composable
        fun m() = SimpleButtonSize(
            heightModifier = Modifier.sizeIn(minWidth = 40.dp, minHeight = 40.dp),
            contentPadding = PaddingValues(13.dp),
            shape = AppTheme.shapes.medium,
            textStyle = AppTheme.specificTypography.labelMedium,
        )

        @Composable
        fun l() = SimpleButtonSize(
            heightModifier = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp),
            contentPadding = PaddingValues(17.dp),
            shape = AppTheme.shapes.medium,
            textStyle = AppTheme.specificTypography.labelLarge,
        )
    }
}

@Immutable
data class SimpleButtonColors(
    private val containerColor: Color,
    private val contentColor: Color,
    private val disabledContainerColor: Color,
    private val disabledContentColor: Color,
) {

    companion object {

        @Composable
        fun default(
            containerColor: Color,
            contentColor: Color,
            disabledContainerColor: Color = AppTheme.specificColorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor: Color = AppTheme.specificColorScheme.onSurface.copy(alpha = 0.38f),
        ) = SimpleButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

        @Composable
        fun primary() = default(
            containerColor = AppTheme.specificColorScheme.primary,
            contentColor = AppTheme.specificColorScheme.onPrimary,
        )

        @Composable
        fun secondary() = default(
            containerColor = AppTheme.specificColorScheme.primaryContainer,
            contentColor = AppTheme.specificColorScheme.onPrimaryContainer,
        )

        @Composable
        fun tonal() = default(
            containerColor = AppTheme.specificColorScheme.surfaceVariant,
            contentColor = AppTheme.specificColorScheme.onSurfaceVariant,
        )

        @Composable
        fun outline() = default(
            containerColor = Color.Transparent,
            contentColor = AppTheme.specificColorScheme.primary,
        )

        @Composable
        fun inline() = default(
            containerColor = Color.Transparent,
            contentColor = AppTheme.specificColorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = AppTheme.specificColorScheme.primaryContainer,
        )

        @Composable
        fun white() = default(
            containerColor = AppTheme.specificColorScheme.surface,
            contentColor = AppTheme.specificColorScheme.onSurface,
        )
    }

    @Composable
    fun containerColor(enabled: Boolean): State<Color> = rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)

    @Composable
    fun contentColor(enabled: Boolean): State<Color> = rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun SimpleButton(
    modifier: Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    size: SimpleButtonSize,
    colors: SimpleButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource,
    border: BorderStroke? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val containerColor = colors.containerColor(enabled).value
    val contentColor = colors.contentColor(enabled).value
    val shadowElevation = /*elevation?.shadowElevation(enabled, interactionSource)?.value ?: */0.dp
    val tonalElevation = /*elevation?.tonalElevation(enabled, interactionSource)?.value ?: */0.dp

    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = size.textStyle)) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Surface(
                modifier = modifier
                    .then(size.heightModifier)
                    .clip(size.shape)
                    .combinedClickable(
                        onClick = onClick,
                        onLongClick = onLongClick,
                        enabled = enabled,
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                    ),
                shape = size.shape,
                color = containerColor,
                contentColor = contentColor,
                tonalElevation = tonalElevation,
                shadowElevation = shadowElevation,
                border = border,
            ) {
                CompositionLocalProvider(LocalContentColor provides contentColor) {
                    ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                        Row(
                            modifier = Modifier.padding(size.contentPadding),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            content = content
                        )
                    }
                }
            }
        }
    }
}