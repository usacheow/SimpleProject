package com.usacheow.coreui.compose.uikit.atom

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.Margin
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.tools.margin

data class SimpleButtonItem(
    val text: TextValue = TextValue.Empty,
    val icon: ImageValue = ImageValue.Empty,
    val type: Type = Type.SIMPLE,
    val modifier: Modifier = Modifier,
    val clickListener: () -> Unit,
) : WidgetState() {

    override val content = @Composable {
        when (type) {
            Type.SIMPLE -> SimpleButtonUnelevated(text, modifier, icon, clickListener)
            Type.TONAL -> SimpleButtonTonal(text, modifier, icon, clickListener)
            Type.OUTLINED -> SimpleButtonOutlined(text, modifier, icon, clickListener)
            Type.TEXT -> SimpleButtonText(text, modifier, icon, clickListener)
            Type.ELEVATION -> SimpleButtonElevated(text, modifier, icon, clickListener)
            Type.TOOLBAR -> SimpleButtonToolbar(icon, clickListener)
        }
    }

    enum class Type {
        SIMPLE, TONAL, OUTLINED, TEXT, ELEVATION, TOOLBAR
    }
}

@Composable
fun SimpleButtonUnelevated(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    clickListener: () -> Unit,
) {
    Button(
        onClick = clickListener,
        modifier = modifier.fillMaxWidth(),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = CommonDimens.elevation_0,
            pressedElevation = CommonDimens.elevation_0),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonTonal(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    clickListener: () -> Unit,
) {
    FilledTonalButton(
        onClick = clickListener,
        modifier = modifier.fillMaxWidth(),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonElevated(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    clickListener: () -> Unit,
) {
    ElevatedButton(
        onClick = clickListener,
        modifier = modifier.fillMaxWidth(),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = CommonDimens.elevation_8,
            pressedElevation = CommonDimens.elevation_4,
        ),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonOutlined(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    clickListener: () -> Unit,
) {
    OutlinedButton(
        onClick = clickListener,
        modifier = modifier.fillMaxWidth(),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonText(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    clickListener: () -> Unit,
) {
    TextButton(
        onClick = clickListener,
        modifier = modifier.fillMaxWidth(),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonToolbar(
    icon: ImageValue,
    clickListener: () -> Unit,
) {
    IconButton(
        onClick = clickListener,
    ) { ButtonContent(TextValue.Empty, icon) }
}

@Composable
private fun ButtonContent(
    text: TextValue,
    icon: ImageValue = ImageValue.Empty,
) {
    val textValue = text.get()
    val iconPaddingEnd = when {
        textValue.isEmpty() -> 0.dp
        else -> 8.dp
    }

    icon.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Button icon",
            tint = LocalContentColor.current,
            modifier = Modifier.padding(end = iconPaddingEnd))
    }
    Text(
        text = textValue,
        color = LocalContentColor.current)
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SimpleButtonPreview() {
    LazySimpleWidgetStatePreview { generatePreviewSimpleButtons() }
}

@Composable
internal fun generatePreviewSimpleButtons(): List<WidgetState> = listOf(
    SimpleButtonItem(
        text = TextValue.Simple("Simple button preview"),
        icon = ImageValue.Empty,
        modifier = Modifier.margin(Margin.Acis(vertical = 8.dp, horizontal = 16.dp)).height(56.dp),
        clickListener = {}),
    SimpleButtonItem(
        text = TextValue.Simple("Simple button preview"),
        icon = ImageValue.Empty,
        type = SimpleButtonItem.Type.TONAL,
        modifier = Modifier.margin(Margin.Acis(vertical = 8.dp, horizontal = 16.dp)),
        clickListener = {}),
    SimpleButtonItem(
        text = TextValue.Simple("Simple elevation button preview"),
        icon = ImageValue.Vector(Icons.Default.Add),
        type = SimpleButtonItem.Type.ELEVATION,
        modifier = Modifier.margin(Margin.Acis(vertical = 8.dp, horizontal = 16.dp)),
        clickListener = {}),
    SimpleButtonItem(
        text = TextValue.Simple("Simple outlined button preview"),
        icon = ImageValue.Vector(Icons.Default.Add),
        type = SimpleButtonItem.Type.OUTLINED,
        modifier = Modifier.margin(Margin.Acis(vertical = 8.dp, horizontal = 16.dp)),
        clickListener = {}),
    SimpleButtonItem(
        text = TextValue.Simple("Simple text button preview"),
        icon = ImageValue.Vector(Icons.Default.Add),
        type = SimpleButtonItem.Type.TEXT,
        modifier = Modifier.margin(Margin.Acis(vertical = 8.dp, horizontal = 16.dp)),
        clickListener = {}),
    SimpleButtonItem(
        icon = ImageValue.Vector(Icons.Default.Feed),
        type = SimpleButtonItem.Type.TOOLBAR,
        clickListener = {}),
)