package com.usacheow.appdemo.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.getBottomInset
import com.usacheow.coreuicompose.tools.getTopInset
import com.usacheow.coreuicompose.uikit.MessageBannerState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MessageFragment : ComposeFragment() {

    @Inject lateinit var router: DemoRouter

    @Composable
    override fun Screen() {
        val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    title = TextValue.Simple("Message tiles"),
                    navigationIcon = R.drawable.ic_back to router::back,
                    contentPadding = getTopInset(),
                    scrollBehavior = scrollBehavior,
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = getBottomInset(),
            ) {
                items(items()) {
                    it.Content(Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                }
            }
        }
    }

    private fun items(): List<WidgetState> = listOf(
        MessageBannerState(
            title = TextValue.Simple("Message title text"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        ),
        MessageBannerState(
            title = TextValue.Simple("Message title text"),
            description = TextValue.Simple("Message description text"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        ),
        MessageBannerState(
            icon = ImageValue.Vector(Icons.Default.Error),
            title = TextValue.Simple("Message title text"),
            description = TextValue.Simple("Message description text"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        ),
        MessageBannerState(
            title = TextValue.Simple("Message title text"),
            button = TextValue.Simple("Button"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            clickListener = {},
        ),
        MessageBannerState(
            title = TextValue.Simple("Message title text"),
            description = TextValue.Simple("Message description text"),
            button = TextValue.Simple("Button"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            clickListener = {},
        ),
        MessageBannerState(
            icon = ImageValue.Vector(Icons.Default.Error),
            title = TextValue.Simple("Message title text"),
            description = TextValue.Simple("Message description text"),
            button = TextValue.Simple("Button"),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            clickListener = {},
        ),
    )
}