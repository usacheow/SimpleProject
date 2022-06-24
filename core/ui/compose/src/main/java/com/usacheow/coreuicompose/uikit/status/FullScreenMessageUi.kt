package com.usacheow.coreuicompose.uikit.status

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.tools.insetAll
import com.usacheow.coreuicompose.tools.mockClick
import com.usacheow.coreuicompose.uikit.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonTonalL
import com.usacheow.coreuitheme.compose.AppTheme

data class FullScreenMessage(
    val statusIcon: ImageValue? = null,
    val icon: ImageValue,
    val title: TextValue,
    val message: TextValue,
    val onDismissed: (() -> Unit)? = null,
    val primaryButton: ButtonData? = null,
    val secondaryButton: ButtonData? = null,
) {

    data class ButtonData(
        val text: TextValue,
        val clickListener: () -> Unit,
    )
}

@Composable
fun FullScreenMessageUi(state: State<FullScreenMessage?>) {
    val value = state.value
    Crossfade(targetState = value != null) { isVisible ->
        if (isVisible && value != null) {
            FullScreenMessageUi(data = value)
        }
    }
}

@Composable
fun FullScreenMessageUi(
    modifier: Modifier = Modifier,
    data: FullScreenMessage,
) {
    BackHandler(data.onDismissed != null) {
        data.onDismissed?.invoke()
    }
    Column(
        modifier = modifier
            .mockClick()
            .background(AppTheme.specificColorScheme.background)
            .fillMaxSize()
            .padding(insetAll())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Visuals(
            icon = data.icon,
            statusIcon = data.statusIcon,
        )
        Text(
            data.title.get(),
            style = AppTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        )
        Text(
            data.message.get(),
            style = AppTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        data.primaryButton?.let { button ->
            SimpleButtonPrimaryL(
                modifier = Modifier.fillMaxWidth(),
                onClick = button.clickListener,
            ) {
                SimpleButtonContent(button.text)
            }
        }
        data.secondaryButton?.let { button ->
            SimpleButtonTonalL(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                onClick = button.clickListener,
            ) {
                SimpleButtonContent(button.text)
            }
        }
    }
}

@Composable
private fun Visuals(
    icon: ImageValue,
    statusIcon: ImageValue?,
) {
    Box(
        modifier = Modifier
            .background(
                color = AppTheme.specificColorScheme.surfaceVariant,
                shape = RoundedCornerShape(40.dp),
            )
            .size(128.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            painter = icon.get(),
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    FullScreenMessageUi(
        modifier = Modifier,
        data = FullScreenMessage(
            icon = AppTheme.specificIcons.account.toImageValue(),
            statusIcon = null,
            title = TextValue.Simple("This is a long title title title title title title title"),
            message = TextValue.Simple("This is a long description description description description description description description description"),
            primaryButton = FullScreenMessage.ButtonData(
                text = TextValue.Simple("Action"),
                clickListener = {},
            ),
            secondaryButton = FullScreenMessage.ButtonData(
                text = TextValue.Simple("Action"),
                clickListener = {},
            ),
        )
    )
}