package com.usacheow.coreui.compose.uikit.atom

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.uikit.container.RowListItem

data class SimpleButtonItem(
    val text: TextValue = TextValue.Empty,
    val icon: ImageValue = ImageValue.Empty,
    val type: Type = Type.Unelevated,
    val size: Size = Size.Mini,
    val modifier: Modifier = Modifier,
    val clickListener: () -> Unit,
) : WidgetState() {

    override val content = @Composable {
        when (type) {
            Type.Unelevated -> SimpleButtonUnelevated(text, modifier, icon, size, clickListener)
            Type.Tonal -> SimpleButtonTonal(text, modifier, icon, size, clickListener)
            Type.Outlined -> SimpleButtonOutlined(text, modifier, icon, size, clickListener)
            Type.Text -> SimpleButtonText(text, modifier, icon, size, clickListener)
            Type.Elevation -> SimpleButtonElevated(text, modifier, icon, size, clickListener)
            Type.Icon -> SimpleButtonIcon(icon, clickListener)
        }
    }

    enum class Type {
        Unelevated, Tonal, Outlined, Text, Elevation, Icon
    }

    enum class Size {
        Max, Mini
    }
}

@Composable
fun SimpleButtonUnelevated(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    size: SimpleButtonItem.Size = SimpleButtonItem.Size.Max,
    clickListener: () -> Unit,
) {

    Button(
        onClick = clickListener,
        modifier = modifier.applySize(size),
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
    size: SimpleButtonItem.Size = SimpleButtonItem.Size.Max,
    clickListener: () -> Unit,
) {
    FilledTonalButton(
        onClick = clickListener,
        modifier = modifier.applySize(size),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonElevated(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    size: SimpleButtonItem.Size = SimpleButtonItem.Size.Max,
    clickListener: () -> Unit,
) {
    ElevatedButton(
        onClick = clickListener,
        modifier = modifier.applySize(size),
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
    size: SimpleButtonItem.Size = SimpleButtonItem.Size.Max,
    clickListener: () -> Unit,
) {
    OutlinedButton(
        onClick = clickListener,
        modifier = modifier.applySize(size),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonText(
    text: TextValue,
    modifier: Modifier = Modifier,
    icon: ImageValue = ImageValue.Empty,
    size: SimpleButtonItem.Size = SimpleButtonItem.Size.Max,
    clickListener: () -> Unit,
) {
    TextButton(
        onClick = clickListener,
        modifier = modifier.applySize(size),
    ) { ButtonContent(text, icon) }
}

@Composable
fun SimpleButtonIcon(
    icon: ImageValue,
    clickListener: () -> Unit,
) {
    icon.get()?.let {
        IconButton(onClick = clickListener,) {
            Icon(
                painter = it,
                contentDescription = "Button icon",
                tint = LocalContentColor.current,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
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

private fun Modifier.applySize(size: SimpleButtonItem.Size): Modifier {
    return when (size) {
        SimpleButtonItem.Size.Max -> fillMaxWidth().defaultMinSize(minHeight = 56.dp)
        SimpleButtonItem.Size.Mini -> wrapContentWidth()
    }
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
        text = TextValue.Simple("Simple button"),
        icon = ImageValue.Empty,
        size = SimpleButtonItem.Size.Max,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        clickListener = {}),
    RowListItem(
        listOf(
            SimpleButtonItem(
                text = TextValue.Simple("Simple button"),
                icon = ImageValue.Empty,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
            SimpleButtonItem(
                icon = ImageValue.Vector(Icons.Default.Add),
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
        )
    ),

    SimpleButtonItem(
        text = TextValue.Simple("Tonal button"),
        icon = ImageValue.Empty,
        type = SimpleButtonItem.Type.Tonal,
        size = SimpleButtonItem.Size.Max,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        clickListener = {}),
    RowListItem(
        listOf(
            SimpleButtonItem(
                text = TextValue.Simple("Tonal button"),
                icon = ImageValue.Empty,
                type = SimpleButtonItem.Type.Tonal,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
            SimpleButtonItem(
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Tonal,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
        )
    ),

    SimpleButtonItem(
        text = TextValue.Simple("Elevation button"),
        icon = ImageValue.Vector(Icons.Default.Add),
        type = SimpleButtonItem.Type.Elevation,
        size = SimpleButtonItem.Size.Max,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        clickListener = {}),
    RowListItem(
        listOf(
            SimpleButtonItem(
                text = TextValue.Simple("Elevation button"),
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Elevation,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
            SimpleButtonItem(
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Elevation,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
        )
    ),

    SimpleButtonItem(
        text = TextValue.Simple("Outlined button"),
        icon = ImageValue.Vector(Icons.Default.Add),
        type = SimpleButtonItem.Type.Outlined,
        size = SimpleButtonItem.Size.Max,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        clickListener = {}),
    RowListItem(
        listOf(
            SimpleButtonItem(
                text = TextValue.Simple("Outlined button"),
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Outlined,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
            SimpleButtonItem(
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Outlined,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
        )
    ),

    SimpleButtonItem(
        text = TextValue.Simple("Text button"),
        icon = ImageValue.Vector(Icons.Default.Add),
        type = SimpleButtonItem.Type.Text,
        size = SimpleButtonItem.Size.Max,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        clickListener = {}),
    RowListItem(
        listOf(
            SimpleButtonItem(
                text = TextValue.Simple("Text button"),
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Text,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
            SimpleButtonItem(
                icon = ImageValue.Vector(Icons.Default.Add),
                type = SimpleButtonItem.Type.Text,
                size = SimpleButtonItem.Size.Mini,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                clickListener = {}),
        )
    ),

    SimpleButtonItem(
        icon = ImageValue.Vector(Icons.Default.Feed),
        type = SimpleButtonItem.Type.Icon,
        clickListener = {}),
)