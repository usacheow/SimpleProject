package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.usacheow.appdemo.DemoRouter
import com.usacheow.corecommon.container.compose.ImageValue
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreui.screen.ComposeFragment
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.ButtonContent
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class ButtonsFragment : ComposeFragment() {

    @Inject
    lateinit var router: DemoRouter

    @Composable
    override fun Screen() {
        val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = TextValue.Simple("Button tiles"),
                    navigationIcon = R.drawable.ic_back to router::back,
                    contentPadding = getTopInset(),
                    scrollBehavior = scrollBehavior,
                )
            }
        ) {
            Column {
                Button(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {},
                ) { ButtonContent(TextValue.Simple("Simple button")) }
                Row {
                    Button(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(TextValue.Simple("Simple button")) }
                    Button(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(null, ImageValue.Vector(Icons.Default.Add)) }
                }

                FilledTonalButton(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {},
                ) { ButtonContent(TextValue.Simple("Tonal button")) }
                Row {
                    FilledTonalButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(TextValue.Simple("Tonal button")) }
                    FilledTonalButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(null, ImageValue.Vector(Icons.Default.Add)) }
                }

                ElevatedButton(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {},
                ) { ButtonContent(TextValue.Simple("Elevated button")) }
                Row {
                    ElevatedButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(TextValue.Simple("Elevated button")) }
                    ElevatedButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(null, ImageValue.Vector(Icons.Default.Add)) }
                }
                OutlinedButton(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {},
                ) { ButtonContent(TextValue.Simple("Outlined button")) }
                Row {
                    OutlinedButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(TextValue.Simple("Outlined button")) }
                    OutlinedButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(null, ImageValue.Vector(Icons.Default.Add)) }
                }

                TextButton(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {},
                ) { ButtonContent(TextValue.Simple("Text button")) }
                Row {
                    TextButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(TextValue.Simple("Text button")) }
                    TextButton(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                        onClick = {},
                    ) { ButtonContent(null, ImageValue.Vector(Icons.Default.Add)) }
                }
                IconButton(onClick = {}) {
                    ButtonContent(null, ImageValue.Vector(Icons.Default.Add))
                }
                Box(modifier = Modifier.padding(getBottomInset()))
            }
        }
    }
}