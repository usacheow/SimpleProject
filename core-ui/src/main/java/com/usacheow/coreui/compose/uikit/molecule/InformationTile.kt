package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.secondaryTextAlpha
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

data class InformationTileItem(
    val image: ImageValue = ImageValue.Empty,
    val additionalLeftText: TextValue = TextValue.Empty,
    val additionalRightText: TextValue = TextValue.Empty,
    val mainLeftText: TextValue = TextValue.Empty,
    val mainRightText: TextValue = TextValue.Empty,
    val clickListener: (() -> Unit)? = null,
) : WidgetState() {

    override val content = @Composable {
        InformationTile(image, additionalLeftText, additionalRightText, mainLeftText, mainRightText, clickListener)
    }

    companion object {
        fun shimmer() = ShimmerTileItem(
            needTopLine = false,
            needRightIcon = false)
    }
}

@Composable
fun InformationTile(
    image: ImageValue = ImageValue.Empty,
    additionalLeftText: TextValue = TextValue.Empty,
    additionalRightText: TextValue = TextValue.Empty,
    mainLeftText: TextValue = TextValue.Empty,
    mainRightText: TextValue = TextValue.Empty,
    clickListener: (() -> Unit)? = null,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        onClick = clickListener ?: {},
        indication = rememberRipple(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(CommonDimens.default_screen_margin),
        ) {
            Icon(icon = image)
            Column(modifier = Modifier.fillMaxWidth()) {
                Row {
                    PrimaryText(mainLeftText, Modifier.weight(1f))
                    PrimaryText(mainRightText, Modifier.wrapContentWidth(), TextAlign.End)
                }
                SpaceTile(height = 4.dp)
                Row {
                    SecondaryText(additionalLeftText, Modifier.weight(1f))
                    SecondaryText(additionalRightText, Modifier.wrapContentWidth(), TextAlign.End)
                }
            }
        }
    }
}

@Composable
private fun Icon(icon: ImageValue) {
    icon.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Action tile icon",
            modifier = Modifier
                .padding(end = CommonDimens.default_screen_margin)
                .size(44.dp))
    }
}

@Composable
private fun PrimaryText(value: TextValue, modifier: Modifier, textAlign: TextAlign = TextAlign.Start) {
    Text(
        text = value.get(),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
        maxLines = 1,
        textAlign = textAlign)
}

@Composable
private fun SecondaryText(value: TextValue, modifier: Modifier, textAlign: TextAlign = TextAlign.Start) {
    CompositionLocalProvider(LocalContentAlpha provides secondaryTextAlpha) {
        Text(
            text = value.get(),
            style = MaterialTheme.typography.labelSmall,
            modifier = modifier,
            maxLines = 1,
            textAlign = textAlign)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InformationTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewInformationTiles() }
}

@Composable
internal fun generatePreviewInformationTiles(): List<WidgetState> = listOf(
    InformationTileItem.shimmer(),
    InformationTileItem(
        image = ImageValue.Empty,
        additionalLeftText = TextValue.Simple("SHANGHAI"),
        additionalRightText = TextValue.Simple("SAT"),
        mainLeftText = TextValue.Simple("Steven Lang"),
        mainRightText = TextValue.Simple("19-03-2021"),
        clickListener = {}),
    InformationTileItem(
        image = ImageValue.Vector(Icons.Default.AccountCircle),
        additionalLeftText = TextValue.Simple("SHANGHAI"),
        additionalRightText = TextValue.Simple("SAT"),
        mainLeftText = TextValue.Simple("Steven Lang"),
        mainRightText = TextValue.Simple("19-03-2021"),
        clickListener = {}),
)