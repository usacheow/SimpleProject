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

object SimpleButtonConfig {

    @Composable
    fun borderOutline() = BorderStroke(width = 1.dp, color = AppTheme.specificColorScheme.border)
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
            textStyle = AppTheme.specificTypography.labelLarge,
        )

        @Composable
        fun m() = SimpleButtonSize(
            heightModifier = Modifier.sizeIn(minWidth = 40.dp, minHeight = 40.dp),
            contentPadding = PaddingValues(13.dp),
            shape = AppTheme.shapes.medium,
            textStyle = AppTheme.specificTypography.labelLarge,
        )

        @Composable
        fun l() = SimpleButtonSize(
            heightModifier = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp),
            contentPadding = PaddingValues(17.dp),
            shape = AppTheme.shapes.medium,
            textStyle = AppTheme.specificTypography.bodyMedium,
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
            disabledContainerColor: Color = AppTheme.specificColorScheme.onSurface1.copy(alpha = 0.12f),
            disabledContentColor: Color = AppTheme.specificColorScheme.onSurface1.copy(alpha = 0.38f),
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
            containerColor = AppTheme.specificColorScheme.secondary,
            contentColor = AppTheme.specificColorScheme.onSecondary,
        )

        @Composable
        fun tonal() = default(
            containerColor = AppTheme.specificColorScheme.surface2,
            contentColor = AppTheme.specificColorScheme.onSurface2,
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
            disabledContentColor = AppTheme.specificColorScheme.symbolSecondary,
        )

        @Composable
        fun white() = default(
            containerColor = AppTheme.specificColorScheme.surface1,
            contentColor = AppTheme.specificColorScheme.onSurface1,
        )
    }

    @Composable
    fun containerColor(enabled: Boolean): State<Color> = rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)

    @Composable
    fun contentColor(enabled: Boolean): State<Color> = rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SimpleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    size: SimpleButtonSize,
    colors: SimpleButtonColors,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
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