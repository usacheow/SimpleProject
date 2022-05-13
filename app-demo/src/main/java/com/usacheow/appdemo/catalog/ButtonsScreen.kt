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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.SimpleButtonContent
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonPrimaryS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonTonalL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonTonalM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonTonalS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonInlineS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonSecondaryL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonSecondaryM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonSecondaryS
import com.usacheow.coreuicompose.uikit.button.SimpleButtonOutlineL
import com.usacheow.coreuicompose.uikit.button.SimpleButtonOutlineM
import com.usacheow.coreuicompose.uikit.button.SimpleButtonOutlineS
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.DimenValues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsScreen(navController: NavHostController) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Buttons"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Primaries()
            DividerBetweenCards()
            Secondaries()
            DividerBetweenCards()
            Tonals()
            DividerBetweenCards()
            Outlines()
            DividerBetweenCards()
            Inlines()

            Box(modifier = Modifier.padding(getBottomInset()))
        }
    }
}

@Composable
private fun Primaries() {
    val icon = AppTheme.specificIcons.add
    Header("Primary")
    Container {
        SimpleButtonPrimaryS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonPrimaryS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonPrimaryS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonPrimaryS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonPrimaryM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonPrimaryM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonPrimaryM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonPrimaryM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonPrimaryL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonPrimaryL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonPrimaryL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonPrimaryL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Secondaries() {
    val icon = AppTheme.specificIcons.add
    Header("Secondary")
    Container {
        SimpleButtonSecondaryS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonSecondaryS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonSecondaryS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonSecondaryS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonSecondaryM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonSecondaryM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonSecondaryM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonSecondaryM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonSecondaryL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonSecondaryL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonSecondaryL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonSecondaryL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Tonals() {
    val icon = AppTheme.specificIcons.add
    Header("Tonal")
    Container {
        SimpleButtonTonalS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonTonalS(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonTonalS(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonTonalS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonTonalM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonTonalM(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonTonalM(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonTonalM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonTonalL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonTonalL(onClick = {}, enabled = false) {
            SimpleButtonContent(TextValue.Simple("Label"))
        }
        SimpleButtonTonalL(onClick = {}) {
            SimpleButtonContent(TextValue.Simple("Label"), iconRight = icon)
        }
        SimpleButtonTonalL(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
}

@Composable
private fun Outlines() {
    val icon = AppTheme.specificIcons.add
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
    val icon = AppTheme.specificIcons.add
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
                .height(DimenValues.radius_extra_large)
                .fillMaxWidth(),
        )
        Box(
            modifier = Modifier
                .background(
                    color = AppTheme.specificColorScheme.surface,
                    shape = AppTheme.shapes.extraLarge.copy(bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)),
                )
                .height(DimenValues.radius_extra_large)
                .fillMaxWidth(),
        )
    }
}