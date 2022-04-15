package com.usacheow.appdemo.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.ButtonContent
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.button.*
import com.usacheow.coreuitheme.R
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Button tiles"),
                navigationIcon = R.drawable.ic_back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Actions()
            DividerBetweenCards()
            Lights()
            DividerBetweenCards()
            Defaults()
            DividerBetweenCards()
            Outlines()
            DividerBetweenCards()
            Inlines()

            Box(modifier = Modifier.padding(getBottomInset()))
        }
    }
}

@Composable
private fun Actions() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Action")
    Container {
        SimpleButtonActionS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionS(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonActionS(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonActionM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionM(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonActionM(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonActionL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionL(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonActionL(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
}

@Composable
private fun Lights() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Light")
    Container {
        SimpleButtonLightS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightS(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonLightS(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonLightM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightM(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonLightM(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonLightL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightL(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonLightL(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
}

@Composable
private fun Defaults() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Default")
    Container {
        SimpleButtonDefaultS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultS(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonDefaultS(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonDefaultM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultM(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonDefaultM(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonDefaultL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultL(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonDefaultL(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
}

@Composable
private fun Outlines() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Outline")
    Container {
        SimpleButtonOutlineS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineS(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonOutlineS(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonOutlineM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineM(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonOutlineM(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonOutlineL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineL(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonOutlineL(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
}

@Composable
private fun Inlines() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Inline")
    Container {
        SimpleButtonInlineS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineS(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineS(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonInlineS(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonInlineM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineM(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineM(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonInlineM(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonInlineL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineL(onClick = {}, enabled = false) {
            ButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineL(onClick = {}) {
            ButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonInlineL(onClick = {}) {
            ButtonContent(null, icon)
        }
    }
}

@Composable
private fun Header(text: String) {
    Text(
        text = text,
        style = AppTheme.typography.titleLarge,
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp),
    )
}

@Composable
private fun Container(content: @Composable RowScope.() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp),
        content = content,
    )
}

@Composable
private fun DividerBetweenCards() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.background(AppTheme.specificColorScheme.surfaceVariant),
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = AppTheme.specificColorScheme.surface,
                    shape = AppTheme.shapes.extraLarge.copy(topEnd = CornerSize(0.dp), topStart = CornerSize(0.dp)),
                )
                .height(Dimen.radius_extra_large)
                .fillMaxWidth(),
        )
        Box(
            modifier = Modifier
                .background(
                    color = AppTheme.specificColorScheme.surface,
                    shape = AppTheme.shapes.extraLarge.copy(bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)),
                )
                .height(Dimen.radius_extra_large)
                .fillMaxWidth(),
        )
    }
}