package com.usacheow.coreuicompose.tools

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.usacheow.corecommon.container.compose.ImageValue
import com.usacheow.corecommon.container.compose.TextValue

@Composable
@ReadOnlyComposable
fun drawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(LocalContext.current, id)
}

@Composable
@ReadOnlyComposable
fun string(@StringRes id: Int): String {
    return stringResource(id)
}

@Composable
@ReadOnlyComposable
fun string(@StringRes id: Int, vararg args: Any): String {
    return LocalContext.current.resources.getString(id, args)
}

@Composable
@ReadOnlyComposable
fun plural(@PluralsRes id: Int, quantity: Int): String {
    return LocalContext.current.resources.getQuantityString(id, quantity)
}

@Composable
@ReadOnlyComposable
fun color(@ColorRes id: Int): Color {
    return colorResource(id)
}

@Composable
@ReadOnlyComposable
fun dimen(@DimenRes id: Int): Dp {
    return LocalContext.current.resources.getDimension(id).dp
}

@Composable
fun ImageValue.get(): Painter? = when (this) {
    is ImageValue.Url, is ImageValue.Image, is ImageValue.ResImage -> rememberImagePainter(
        data = value,
        builder = {
            crossfade(true)
        }
    )

    is ImageValue.ResVector -> rememberVectorPainter(image = ImageVector.vectorResource(id = value as Int))

    is ImageValue.Vector -> rememberVectorPainter(image = value as ImageVector)

    else -> null
}

@Composable
fun TextValue.get(): AnnotatedString = when (this) {
    is TextValue.Simple -> AnnotatedString(value)

    is TextValue.Annotated -> value

    is TextValue.Res -> AnnotatedString(string(value, *args.toTypedArray()))

    is TextValue.Plural -> AnnotatedString(plural(value, quantity))

    is TextValue.Empty -> AnnotatedString("")
}