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
import com.usacheow.coreuicompose.uikit.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.button.SimpleButtonActionL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonActionM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonActionS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonDefaultL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonDefaultM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonDefaultS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonLightL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonLightM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonLightS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonOutlineL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonOutlineM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonOutlineS
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
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonActionS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonActionM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonActionM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonActionL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonActionL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonActionL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Lights() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Light")
    Container {
        SimpleButtonLightS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonLightS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonLightM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonLightM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonLightL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonLightL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonLightL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Defaults() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Default")
    Container {
        SimpleButtonDefaultS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonDefaultS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonDefaultM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonDefaultM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonDefaultL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonDefaultL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonDefaultL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Outlines() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Outline")
    Container {
        SimpleButtonOutlineS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonOutlineS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonOutlineM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonOutlineM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonOutlineL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonOutlineL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonOutlineL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Inlines() {
    val icon = ImageValue.Vector(Icons.Default.Add)
    Header("Inline")
    Container {
        SimpleButtonInlineS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonInlineS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonInlineM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonInlineM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonInlineL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonInlineL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonInlineL(onClick = {}) {
            SimpleButtonContent(null, icon)
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