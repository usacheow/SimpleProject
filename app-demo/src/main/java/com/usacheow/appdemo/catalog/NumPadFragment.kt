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
import com.usacheow.appdemo.DemoRouter
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreui.screen.ComposeFragment
import com.usacheow.coreuicompose.tools.defaultBorder
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.NumPad
import com.usacheow.coreuicompose.uikit.NumPadActionMode
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import com.usacheow.coreuitheme.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class NumPadFragment : ComposeFragment() {

    @Inject
    lateinit var router: DemoRouter

    @Composable
    override fun Screen() {
        val enteredCode = remember { mutableStateOf("") }
        val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = TextValue.Simple("Num pad"),
                    navigationIcon = R.drawable.ic_back to router::back,
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
}