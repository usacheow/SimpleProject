package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.defaultBorder
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.PinCodeIndicator
import com.usacheow.coreuicompose.uikit.button.NumPad
import com.usacheow.coreuicompose.uikit.button.NumPadAction
import com.usacheow.coreuicompose.uikit.duplicate.SimpleTopAppBar
import com.usacheow.coreuitheme.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumPadScreen(navController: NavHostController) {
    val enteredCode = remember { mutableStateOf("") }
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                title = TextValue.Simple("Num pad"),
                navigationIcon = AppTheme.specificIcons.back to navController::popBackStack,
                contentPadding = getTopInset(),
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(getBottomInset()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Box(
                modifier = Modifier
                    .defaultBorder(AppTheme.shapes.medium)
                    .padding(8.dp)
                    .width(150.dp),
            ) {
                Text(
                    text = enteredCode.value,
                    style = AppTheme.typography.displayLarge,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            PinCodeIndicator(
                modifier = Modifier.padding(top = 20.dp),
                filledCirclesCount = enteredCode.value.length,
            )
            NumPad(
                action = NumPadAction.delete { enteredCode.value = enteredCode.value.dropLast(1) },
                onForgetClick = {},
                onNumberClick = { enteredCode.value = (enteredCode.value + it).take(4) },
            )
        }
    }
}