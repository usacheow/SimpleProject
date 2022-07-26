package com.usacheow.coreuicompose.uikit.listtile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.button.SimpleButtonContent
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.PreviewAppTheme

data class MessageBannerState(
    val icon: ImageValue? = null,
    val title: TextValue? = null,
    val description: TextValue? = null,
    val button: TextValue? = null,
    val modifier: Modifier = Modifier,
    val onClick: (() -> Unit)? = null,
) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        MessageBannerTile(modifier, this)
    }
}

object MessageBannerConfig {

    @Composable
    fun colors() = CardDefaults.cardColors(
        containerColor = AppTheme.specificColorScheme.surfaceVariant,
        contentColor = AppTheme.specificColorScheme.onSurfaceVariant,
    )

    @Composable
    fun elevation() = CardDefaults.cardElevation()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageBannerTile(
    modifier: Modifier = Modifier,
    data: MessageBannerState,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = MessageBannerConfig.colors(),
        elevation = MessageBannerConfig.elevation(),
        shape = AppTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val iconPainter = data.icon?.get()

            iconPainter?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(it)
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (data.title != null) {
                if (iconPainter != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Title(data.title)
                if (iconPainter != null && data.description == null && data.button == null) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (data.description != null) {
                when {
                    data.title != null -> Spacer(modifier = Modifier.height(4.dp))
                    iconPainter != null -> Spacer(modifier = Modifier.height(8.dp))
                }
                Description(data.description)
                when {
                    iconPainter != null && data.button == null -> Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (data.button != null) {
                when {
                    data.title != null || data.description != null -> Spacer(modifier = Modifier.height(16.dp))
                    iconPainter != null -> Spacer(modifier = Modifier.height(8.dp))
                }
                Button(data.button, data.onClick ?: {})
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
        color = AppTheme.specificColorScheme.symbolPrimary,
        style = AppTheme.specificTypography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun Description(description: TextValue) {
    Text(
        text = description.get(),
        color = AppTheme.specificColorScheme.symbolSecondary,
        style = AppTheme.specificTypography.bodyMedium,
        textAlign = TextAlign.Center,
        maxLines = 3,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun Button(button: TextValue, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        onClick = onClick,
    ) { SimpleButtonContent(button) }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        MessageBannerState(
            icon = AppTheme.specificIcons.error.toImageValue(),
            title = "Message title text".textValue(),
            description = "Message description text".textValue(),
            button = "Button".textValue(),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = {},
        ).Content(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
    }
}