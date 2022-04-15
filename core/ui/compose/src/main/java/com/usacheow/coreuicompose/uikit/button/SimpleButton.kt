package com.usacheow.coreuicompose.uikit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.usacheow.coreuitheme.compose.AppTheme

object SimpleButtonDefaults {

    val heightModifierS = Modifier.sizeIn(minWidth = 32.dp, minHeight = 32.dp)
    val heightModifierM = Modifier.sizeIn(minWidth = 40.dp, minHeight = 40.dp)
    val heightModifierL = Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp)

    val ShapeS = RoundedCornerShape(50)
    val ShapeM = RoundedCornerShape(10.dp)
    val ShapeL = RoundedCornerShape(12.dp)

    val ContentPaddingS = PaddingValues(10.dp)
    val ContentPaddingM = PaddingValues(13.dp)
    val ContentPaddingL = PaddingValues(17.dp)

    @Composable
    fun elevation() = ButtonDefaults.buttonElevation()

    @Composable
    fun colorsAction() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.primary,
        contentColor = AppTheme.specificColorScheme.onPrimary,
    )

    @Composable
    fun colorsLight() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.primaryContainer,
        contentColor = AppTheme.specificColorScheme.onPrimaryContainer,
    )

    @Composable
    fun colorsDefault() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.specificColorScheme.surfaceVariant,
        contentColor = AppTheme.specificColorScheme.onSurfaceVariant,
    )

    @Composable
    fun colorsOutline() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.specificColorScheme.primaryContainer,
    )

    @Composable
    fun colorsInline() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.specificColorScheme.primary,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = AppTheme.specificColorScheme.primaryContainer,
    )

    @Composable
    fun borderOutline() = BorderStroke(width = 1.dp, color = AppTheme.specificColorScheme.outline)

    @Composable
    fun textStyleS() = AppTheme.typography.labelMedium

    @Composable
    fun textStyleM() = AppTheme.typography.labelMedium

    @Composable
    fun textStyleL() = AppTheme.typography.labelLarge
}

@Composable
fun SimpleButtonActionS(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleS())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeS,
            colors = SimpleButtonDefaults.colorsAction(),
            contentPadding = SimpleButtonDefaults.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonActionM(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleM())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeM,
            colors = SimpleButtonDefaults.colorsAction(),
            contentPadding = SimpleButtonDefaults.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonActionL(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleL())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeL,
            colors = SimpleButtonDefaults.colorsAction(),
            contentPadding = SimpleButtonDefaults.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonLightS(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleS())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeS,
            colors = SimpleButtonDefaults.colorsLight(),
            contentPadding = SimpleButtonDefaults.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonLightM(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleM())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeM,
            colors = SimpleButtonDefaults.colorsLight(),
            contentPadding = SimpleButtonDefaults.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonLightL(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleL())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeL,
            colors = SimpleButtonDefaults.colorsLight(),
            contentPadding = SimpleButtonDefaults.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonDefaultS(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleS())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeS,
            colors = SimpleButtonDefaults.colorsDefault(),
            contentPadding = SimpleButtonDefaults.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonDefaultM(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleM())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeM,
            colors = SimpleButtonDefaults.colorsDefault(),
            contentPadding = SimpleButtonDefaults.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonDefaultL(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleL())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeL,
            colors = SimpleButtonDefaults.colorsDefault(),
            contentPadding = SimpleButtonDefaults.ContentPaddingL,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonOutlineS(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleS())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeS,
            colors = SimpleButtonDefaults.colorsOutline(),
            contentPadding = SimpleButtonDefaults.ContentPaddingS,
            border = if (enabled) SimpleButtonDefaults.borderOutline() else null,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonOutlineM(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleM())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeM,
            colors = SimpleButtonDefaults.colorsOutline(),
            contentPadding = SimpleButtonDefaults.ContentPaddingM,
            border = if (enabled) SimpleButtonDefaults.borderOutline() else null,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonOutlineL(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleL())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeL,
            colors = SimpleButtonDefaults.colorsOutline(),
            contentPadding = SimpleButtonDefaults.ContentPaddingL,
            border = if (enabled) SimpleButtonDefaults.borderOutline() else null,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonInlineS(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleS())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierS),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeS,
            colors = SimpleButtonDefaults.colorsInline(),
            contentPadding = SimpleButtonDefaults.ContentPaddingS,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonInlineM(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleM())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierM),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeM,
            colors = SimpleButtonDefaults.colorsInline(),
            contentPadding = SimpleButtonDefaults.ContentPaddingM,
            content = content,
        )
    }
}

@Composable
fun SimpleButtonInlineL(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    MaterialTheme(typography = MaterialTheme.typography.copy(labelLarge = SimpleButtonDefaults.textStyleL())) {
        Button(
            onClick = onClick,
            modifier = modifier.then(SimpleButtonDefaults.heightModifierL),
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = SimpleButtonDefaults.elevation(),
            shape = SimpleButtonDefaults.ShapeL,
            colors = SimpleButtonDefaults.colorsInline(),
            contentPadding = SimpleButtonDefaults.ContentPaddingL,
            content = content,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Button(
    onClick: () -> Unit,
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
            onClick = onClick,
            modifier = Modifier,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            shadowElevation = shadowElevation,
            border = border,
            interactionSource = interactionSource,
            enabled = enabled,
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                    Row(
                        modifier = modifier.padding(contentPadding),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        content = content
                    )
                }
            }
        }
    }
}