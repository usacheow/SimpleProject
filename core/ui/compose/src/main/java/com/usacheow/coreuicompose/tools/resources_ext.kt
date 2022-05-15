package com.usacheow.coreuicompose.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import coil.compose.rememberImagePainter
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue

@Composable
fun ImageValue.get(): Painter = when (this) {
    is ImageValue.Url, is ImageValue.Image, is ImageValue.ResImage -> rememberImagePainter(
        data = value,
        builder = {
            crossfade(true)
        }
    )

    is ImageValue.ResVector -> rememberVectorPainter(image = ImageVector.vectorResource(id = value as Int))

    is ImageValue.Vector -> rememberVectorPainter(image = value as ImageVector)
}

@Composable
fun IconValue.get(): Painter = when (this) {
    is IconValue.ResVector -> rememberVectorPainter(image = ImageVector.vectorResource(id = value as Int))

    is IconValue.Vector -> rememberVectorPainter(image = value as ImageVector)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextValue.get(): AnnotatedString = when (this) {
    is TextValue.Simple -> AnnotatedString(value)

    is TextValue.Annotated -> value

    is TextValue.Res -> AnnotatedString(stringResource(value, *args.toTypedArray()))

    is TextValue.Plural -> AnnotatedString(pluralStringResource(value, quantity).format(*args.toTypedArray()))
}