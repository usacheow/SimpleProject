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
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.textValue
import com.usacheow.coreuicompose.tools.insetAllExcludeBottom
import com.usacheow.coreuicompose.tools.insetBottom
import com.usacheow.coreuicompose.tools.insetHorizontal
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = "Buttons".textValue(),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = insetAllExcludeBottom(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(insetHorizontal()),
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

            Box(modifier = Modifier.padding(insetBottom()))
        }
    }
}

@Composable
private fun Primaries() {
    val icon = AppTheme.specificIcons.add
    Header("Primary")
    Container {
        SimpleButtonPrimaryS(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonPrimaryS(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonPrimaryS(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonPrimaryS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonPrimaryM(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonPrimaryM(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonPrimaryM(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonPrimaryM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonPrimaryL(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonPrimaryL(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonPrimaryL(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
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
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonSecondaryS(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonSecondaryS(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonSecondaryS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonSecondaryM(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonSecondaryM(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonSecondaryM(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonSecondaryM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonSecondaryL(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonSecondaryL(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonSecondaryL(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
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
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonTonalS(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonTonalS(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonTonalS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonTonalM(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonTonalM(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonTonalM(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonTonalM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonTonalL(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonTonalL(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonTonalL(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
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
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonOutlineS(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonOutlineS(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonOutlineS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonOutlineM(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonOutlineM(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonOutlineM(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonOutlineM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonOutlineL(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonOutlineL(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonOutlineL(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
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
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonInlineS(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonInlineS(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonInlineS(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonInlineM(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonInlineM(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonInlineM(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
        }
        SimpleButtonInlineM(onClick = {}) {
            SimpleButtonContent(null, icon)
        }
    }
    Container {
        SimpleButtonInlineL(onClick = {}) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonInlineL(onClick = {}, enabled = false) {
            SimpleButtonContent("Label".textValue())
        }
        SimpleButtonInlineL(onClick = {}) {
            SimpleButtonContent("Label".textValue(), iconRight = icon)
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