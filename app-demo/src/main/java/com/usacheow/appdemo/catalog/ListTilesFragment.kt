package com.usacheow.appdemo.catalog

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.NavigateNext
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
import com.usacheow.coreuicompose.uikit.ListTileState
import com.usacheow.coreuicompose.uikit.SimpleTopAppBar
import com.usacheow.coreuitheme.R
import com.usacheow.coreuitheme.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class ListTilesFragment : ComposeFragment() {

    @Inject lateinit var router: DemoRouter

    @Composable
    override fun Screen() {
        val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                SimpleTopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = TextValue.Simple("List tiles"),
                    navigationIcon = R.drawable.ic_back to router::back,
                    contentPadding = getTopInset(),
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = getBottomInset(),
            ) {
                items(items()) {
                    it.Content(Modifier)
                }
            }
        }
    }

    private fun items(): List<WidgetState> = listOf(
        ListTileState.shimmer(),
        ListTileState(
            value = TextValue.Simple("Main information"),
        ),
        ListTileState(
            value = TextValue.Simple("Main information"),
            bottomDescription = TextValue.Simple("Bottom description"),
        ),
        ListTileState(
            value = TextValue.Simple("Main information"),
            topDescription = TextValue.Simple("Top description"),
            bottomDescription = TextValue.Simple("Bottom description"),
        ),
        ListTileState(
            rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
            value = TextValue.Simple("Main information"),
            clickListener = {},
        ),
        ListTileState(
            leftImageInfo = ImageValue.Vector(Icons.Default.AccountCircle),
            rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
            value = TextValue.Simple("Main information"),
            bottomDescription = TextValue.Simple("Bottom description"),
            clickListener = {},
        ),
        ListTileState(
            leftImageInfo = ImageValue.Vector(Icons.Default.AccountCircle),
            rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
            value = TextValue.Simple("Main information"),
            topDescription = TextValue.Simple("Top description"),
            bottomDescription = TextValue.Simple("Bottom description"),
            clickListener = {},
        ),
    )
}