package com.usacheow.coreuicompose.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue

@Composable
fun ImageValue.get(builder: ImageRequest.Builder.() -> Unit = {}): Painter = when (this) {
    is ImageValue.Vector -> rememberVectorPainter(image = value as ImageVector)

    else -> rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(value)
            .apply { builder() }
            .build()
    )
}

@Composable
fun IconValue.get(builder: ImageRequest.Builder.() -> Unit = {}): Painter = toImageValue().get(builder)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextValue.get(): AnnotatedString = when (this) {
    is TextValue.Simple -> AnnotatedString(value)

    is TextValue.Annotated -> value

    is TextValue.Res -> AnnotatedString(stringResource(value, *args.toTypedArray()))

    is TextValue.Plural -> AnnotatedString(pluralStringResource(value, quantity).format(*args.toTypedArray()))
}