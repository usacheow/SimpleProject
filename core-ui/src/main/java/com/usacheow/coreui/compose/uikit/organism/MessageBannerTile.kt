package com.usacheow.coreui.compose.uikit.organism

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.resources.Dimen
import com.usacheow.core.resource.compose.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.core.resource.compose.TextValue
import com.usacheow.coreui.compose.tools.get
import com.usacheow.coreui.compose.uikit.atom.SimpleButtonOutlined
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

data class MessageBannerState(
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
        modifier = modifier.fillMaxWidth(),
        backgroundColor = AppTheme.commonColors.surfaceVariant,
        contentColor = AppTheme.commonColors.onSurfaceVariant,
        elevation = Dimen.elevation_0,
        shape = AppTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val iconPainter = icon.get()
            val hasIcon = iconPainter != null
            val hasTitle = title !is TextValue.Empty
            val hasDescription = description !is TextValue.Empty
            val hasButton = button !is TextValue.Empty

            iconPainter?.let {
                SpaceTile(height = 8.dp)
                Icon(it)
                SpaceTile(height = 8.dp)
            }
            if (hasTitle) {
                when {
                    hasIcon -> SpaceTile(height = 8.dp)
                }
                Title(title)
                when {
                    hasIcon && !hasDescription && !hasButton -> SpaceTile(height = 8.dp)
                }
            }
            if (hasDescription) {
                when {
                    hasTitle -> SpaceTile(height = 4.dp)
                    hasIcon -> SpaceTile(height = 8.dp)
                }
                Description(description)
                when {
                    hasIcon && !hasButton -> SpaceTile(height = 8.dp)
                }
            }
            if (hasButton) {
                when {
                    hasTitle || hasDescription -> SpaceTile(height = 16.dp)
                    hasIcon -> SpaceTile(height = 8.dp)
                }
                Button(button, clickListener)
            }
        }
    }
}

@Composable
private fun Icon(icon: Painter) {
    Icon(
        painter = icon,
        contentDescription = "Message banner icon",
        modifier = Modifier.size(52.dp),
    )
}

@Composable
private fun Title(title: TextValue) {
    Text(
        text = title.get(),
        color = AppTheme.commonColors.symbolPrimary,
        style = AppTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun Description(description: TextValue) {
    Text(
        text = description.get(),
        color = AppTheme.commonColors.symbolSecondary,
        style = AppTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        maxLines = 3,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun Button(button: TextValue, clickListener: (() -> Unit)?) {
    SimpleButtonOutlined(
        text = button,
        clickListener = clickListener ?: {},
        modifier = Modifier.padding(horizontal = 16.dp),
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MessageBannerTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewMessageBanners() }
}

@Composable
private fun generatePreviewMessageBanners(): List<WidgetState> = listOf(
    MessageBannerState(
        title = TextValue.Simple("Message title text"),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ),
    MessageBannerState(
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text"),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ),
    MessageBannerState(
        icon = ImageValue.Vector(Icons.Default.Error),
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text"),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ),
    MessageBannerState(
        title = TextValue.Simple("Message title text"),
        button = TextValue.Simple("Button"),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        clickListener = {},
    ),
    MessageBannerState(
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text"),
        button = TextValue.Simple("Button"),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        clickListener = {},
    ),
    MessageBannerState(
        icon = ImageValue.Vector(Icons.Default.Error),
        title = TextValue.Simple("Message title text"),
        description = TextValue.Simple("Message description text"),
        button = TextValue.Simple("Button"),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        clickListener = {},
    ),
)