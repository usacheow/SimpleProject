package com.usacheow.coreuicompose.uikit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.usacheow.coreuitheme.compose.AppTheme

object SimpleButtonConfig {

    val heightModifierS = Modifier.sizeIn(minWidth = 32.dp, minHeight = 32.dp)
    val heightModifierM = Modifier.sizeIn(minWidth = 40.dp, minHeight = 40.dp)
    val heightModifierL = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp)

    val ContentPaddingS = PaddingValues(10.dp)
    val ContentPaddingM = PaddingValues(13.dp)
    val ContentPaddingL = PaddingValues(17.dp)

    @Composable
    fun shapeS() = CircleShape

    @Composable
    fun shapeM() = AppTheme.shapes.medium

    @Composable
    fun shapeL() = AppTheme.shapes.medium

    @Composable
    fun elevation() = ButtonDefaults.buttonElevation()

    @Composable
    fun colorsPrimary() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.primary,
        contentColor = AppTheme.specificColorScheme.onPrimary,
    )

    @Composable
    fun colorsSecondary() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.primaryContainer,
        contentColor = AppTheme.specificColorScheme.onPrimaryContainer,
    )

    @Composable
    fun colorsTonal() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.surfaceVariant,
        contentColor = AppTheme.specificColorScheme.onSurfaceVariant,
    )

    @Composable
    fun colorsOutline() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.specificColorScheme.primary,
    )

    @Composable
    fun colorsInline() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.specificColorScheme.primary,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = AppTheme.specificColorScheme.primaryContainer,
    )

    @Composable
    fun colorsWhite() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.surface,
        contentColor = AppTheme.specificColorScheme.onSurface,
    )

    @Composable
    fun borderOutline() = BorderStroke(width = 1.dp, color = AppTheme.specificColorScheme.outline)

    @Composable
    fun textStyleS() = AppTheme.specificTypography.labelMedium

    @Composable
    fun textStyleM() = AppTheme.specificTypography.labelMedium

    @Composable
    fun textStyleL() = AppTheme.specificTypography.labelLarge
}

@Composable
fun SimpleButtonPrimaryS(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleS())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeS(),
            colors = SimpleButtonConfig.colorsPrimary(),
            contentPadding = SimpleButtonConfig.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonPrimaryM(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleM())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeM(),
            colors = SimpleButtonConfig.colorsPrimary(),
            contentPadding = SimpleButtonConfig.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonPrimaryL(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleL())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeL(),
            colors = SimpleButtonConfig.colorsPrimary(),
            contentPadding = SimpleButtonConfig.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonSecondaryS(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleS())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeS(),
            colors = SimpleButtonConfig.colorsSecondary(),
            contentPadding = SimpleButtonConfig.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonSecondaryM(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleM())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeM(),
            colors = SimpleButtonConfig.colorsSecondary(),
            contentPadding = SimpleButtonConfig.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonSecondaryL(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleL())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeL(),
            colors = SimpleButtonConfig.colorsSecondary(),
            contentPadding = SimpleButtonConfig.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonTonalS(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleS())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeS(),
            colors = SimpleButtonConfig.colorsTonal(),
            contentPadding = SimpleButtonConfig.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonTonalM(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleM())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeM(),
            colors = SimpleButtonConfig.colorsTonal(),
            contentPadding = SimpleButtonConfig.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonTonalL(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleL())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeL(),
            colors = SimpleButtonConfig.colorsTonal(),
            contentPadding = SimpleButtonConfig.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonOutlineS(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleS())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeS(),
            colors = SimpleButtonConfig.colorsOutline(),
            contentPadding = SimpleButtonConfig.ContentPaddingS,
            border = if (enabled) SimpleButtonConfig.borderOutline() else null,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonOutlineM(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleM())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeM(),
            colors = SimpleButtonConfig.colorsOutline(),
            contentPadding = SimpleButtonConfig.ContentPaddingM,
            border = if (enabled) SimpleButtonConfig.borderOutline() else null,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonOutlineL(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleL())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeL(),
            colors = SimpleButtonConfig.colorsOutline(),
            contentPadding = SimpleButtonConfig.ContentPaddingL,
            border = if (enabled) SimpleButtonConfig.borderOutline() else null,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonInlineS(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleS())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeS(),
            colors = SimpleButtonConfig.colorsInline(),
            contentPadding = SimpleButtonConfig.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonInlineM(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleM())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeM(),
            colors = SimpleButtonConfig.colorsInline(),
            contentPadding = SimpleButtonConfig.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonInlineL(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleL())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeL(),
            colors = SimpleButtonConfig.colorsInline(),
            contentPadding = SimpleButtonConfig.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonWhiteS(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleS())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeS(),
            colors = SimpleButtonConfig.colorsWhite(),
            contentPadding = SimpleButtonConfig.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonWhiteM(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleM())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeM(),
            colors = SimpleButtonConfig.colorsWhite(),
            contentPadding = SimpleButtonConfig.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonWhiteL(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonConfig.textStyleL())) {
        Button(
            onClick = onClick,
            onLongClick = onLongClick,
            modifier = modifier.then(SimpleButtonConfig.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonConfig.elevation(),
            shape = SimpleButtonConfig.shapeL(),
            colors = SimpleButtonConfig.colorsWhite(),
            contentPadding = SimpleButtonConfig.ContentPaddingL,
            content = content,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun Button(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier,
    enabled: Boolean,
    interactionSource: MutableInteractionSource,
    elevation: ButtonElevation?,
    shape: Shape,
    border: BorderStroke? = null,
    colors: ButtonColors,
    contentPadding: PaddingValues,
    content: @Composable RowScope.() -> Unit,
) {
    val containerColor = colors.containerColor(enabled).value
    val contentColor = colors.contentColor(enabled).value
    val shadowElevation = elevation?.shadowElevation(enabled, interactionSource)?.value ?: 0.dp
    val tonalElevation = elevation?.tonalElevation(enabled, interactionSource)?.value ?: 0.dp

    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        Surface(
            modifier = modifier
                .clip(shape)
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = rememberRipple(),
                ),
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            shadowElevation = shadowElevation,
            border = border,
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                    Row(
                        modifier = Modifier.padding(contentPadding),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        content = content
                    )
                }
            }
        }
    }
}