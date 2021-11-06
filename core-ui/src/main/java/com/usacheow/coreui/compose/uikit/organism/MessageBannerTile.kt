package com.usacheow.coreui.compose.uikit.organism

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.LocalCommonColors
import com.usacheow.coreui.compose.resources.Shapes
import com.usacheow.coreui.compose.resources.secondaryTextAlpha
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.uikit.atom.SimpleButtonOutlined
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

data class MessageBannerItem(
    val icon: ImageValue = ImageValue.Empty,
    val title: TextValue = TextValue.Empty,
    val description: TextValue = TextValue.Empty,
    val button: TextValue = TextValue.Empty,
    val modifier: Modifier = Modifier,
    val clickListener: (() -> Unit)? = null,
) : WidgetState() {
    override val content = @Composable {
        MessageBannerTile(modifier, icon, title, description, button, clickListener)
    }
}

@Composable
fun MessageBannerTile(
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    title: TextValue = TextValue.Empty,
    description: TextValue = TextValue.Empty,
    button: TextValue = TextValue.Empty,
    clickListener: (() -> Unit)? = null,
) {
    Card(
        modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        backgroundColor = LocalCommonColors.current.surfaceVariant,
        contentColor = LocalCommonColors.current.onSurfaceVariant,
        elevation = CommonDimens.elevation_0,
        shape = Shapes.medium,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            icon.get()?.let {
                Icon(
                    painter = it,
                    contentDescription = "Message banner icon",
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp).size(52.dp))
            }
            if (title !is TextValue.Empty) {
                Text(
                    text = title.get(),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth())
            }
            if (description !is TextValue.Empty) {
                CompositionLocalProvider(LocalContentAlpha provides secondaryTextAlpha) {
                    SpaceTile(height = 4.dp)
                    Text(
                        text = description.get(),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp).fillMaxWidth())
                }
            }
            if (button !is TextValue.Empty) {
                SimpleButtonOutlined(
                    text = button,
                    clickListener = clickListener ?: {},
                    modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth())
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MessageBannerTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewMessageBanners() }
}

@Composable
internal fun generatePreviewMessageBanners(): List<WidgetState> = listOf(
    MessageBannerItem(
        title = TextValue.Simple("Message title text")),
    MessageBannerItem(
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text")),
    MessageBannerItem(
        icon = ImageValue.Vector(Icons.Default.Error),
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text")),
    MessageBannerItem(
        title = TextValue.Simple("Message title text"),
        button = TextValue.Simple("Button"),
        clickListener = {}),
    MessageBannerItem(
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text"),
        button = TextValue.Simple("Button"),
        clickListener = {}),
    MessageBannerItem(
        icon = ImageValue.Vector(Icons.Default.Error),
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text"),
        button = TextValue.Simple("Button"),
        clickListener = {}),
)