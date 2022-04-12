package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
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
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.compose.AppTheme

data class MessageBannerState(
    val icon: ImageValue? = null,
    val title: TextValue? = null,
    val description: TextValue? = null,
    val button: TextValue? = null,
    val modifier: Modifier = Modifier,
    val clickListener: (() -> Unit)? = null,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        MessageBannerTile(modifier, this)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageBannerTile(
    modifier: Modifier = Modifier,
    data: MessageBannerState,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        containerColor = AppTheme.commonColors.surfaceVariant,
        contentColor = AppTheme.commonColors.onSurfaceVariant,
        elevation = CardDefaults.cardElevation(),
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
private fun Preview() {
    SimplePreview {
        MessageBannerState(
            icon = ImageValue.Vector(Icons.Default.Error),
            title = TextValue.Simple("Message title text"),
            description = TextValue.Simple("Message description text"),
            button = TextValue.Simple("Button"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            clickListener = {},
        ).Content(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
    }
}