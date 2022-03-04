package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
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
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.Dimen
import com.usacheow.corecommon.container.compose.ImageValue
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.get

data class MessageBannerState(
    val icon: ImageValue = ImageValue.Empty,
    val title: TextValue = TextValue.Empty,
    val description: TextValue = TextValue.Empty,
    val button: TextValue = TextValue.Empty,
    val modifier: Modifier = Modifier,
    val clickListener: (() -> Unit)? = null,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        MessageBannerTile(modifier, this)
    }
}

@Composable
fun MessageBannerTile(
    modifier: Modifier = Modifier,
    data: MessageBannerState,
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
            val iconPainter = data.icon.get()
            val hasIcon = iconPainter != null
            val hasTitle = data.title !is TextValue.Empty
            val hasDescription = data.description !is TextValue.Empty
            val hasButton = data.button !is TextValue.Empty

            iconPainter?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(it)
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (hasTitle) {
                when {
                    hasIcon -> Spacer(modifier = Modifier.height(8.dp))
                }
                Title(data.title)
                when {
                    hasIcon && !hasDescription && !hasButton -> Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (hasDescription) {
                when {
                    hasTitle -> Spacer(modifier = Modifier.height(4.dp))
                    hasIcon -> Spacer(modifier = Modifier.height(8.dp))
                }
                Description(data.description)
                when {
                    hasIcon && !hasButton -> Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (hasButton) {
                when {
                    hasTitle || hasDescription -> Spacer(modifier = Modifier.height(16.dp))
                    hasIcon -> Spacer(modifier = Modifier.height(8.dp))
                }
                Button(data.button, data.clickListener ?: {})
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
private fun Button(button: TextValue, clickListener: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        onClick = clickListener,
    ) { ButtonContent(button) }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MessageBannerTilePreview() {
    SimplePreview {
        LazyColumn {
            items(previewMessageBanners()) {
                it.Content(Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }
        }
    }
}

private fun previewMessageBanners(): List<WidgetState> = listOf(
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