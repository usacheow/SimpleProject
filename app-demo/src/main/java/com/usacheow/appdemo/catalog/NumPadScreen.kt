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
import com.usacheow.coreuicompose.uikit.NumPad
import com.usacheow.coreuicompose.uikit.NumPadActionMode
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.R
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
                navigationIcon = R.drawable.ic_back to navController::popBackStack,
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
                    .padding(bottom = 16.dp)
                    .defaultBorder()
                    .padding(8.dp)
                    .width(150.dp),
            ) {
                Text(
                    text = enteredCode.value,
                    style = AppTheme.typography.displayLarge,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            NumPad(
                mode = NumPadActionMode.Accept,
                onBackspaceClick = { enteredCode.value = enteredCode.value.dropLast(1) },
                onActionClick = {},
                onNumberClick = { enteredCode.value = (enteredCode.value + it).take(4) },
            )
        }
    }
}